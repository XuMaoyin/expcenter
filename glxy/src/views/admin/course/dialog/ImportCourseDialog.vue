<template>
	<el-dialog title="批量导入教师信息" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
		<el-form ref='addCourseForm' label-width="5px">

			<el-form-item>
				请先<a href="http://localhost:8002/course.xlsx">下载</a>模板，然后根据模板填入数据，否则会导入不成功
        <a class="el-button" style="position: relative;width: 130px;margin-left: 30px">选择文件
          <input
              type="file"
              ref="upload"
              accept=".xls, .xlsx"
              class="el-button el-button--primary"
              style="opacity: 0;position:absolute;top: 0px;left: 0px;width: 130px"
              @change="readExcel"
          />
        </a>
			</el-form-item>
      <el-table :data="toJson">
        <el-table-column prop="name" label="课程名"></el-table-column>
        <el-table-column prop="teacher" label="任课教师"></el-table-column>
        <el-table-column prop="class" label="班级"></el-table-column>
        <el-table-column prop="experiment" label="实验名称"></el-table-column>
        <el-table-column prop="date" label="实验日期"></el-table-column>
        <el-table-column prop="number" label="节次"></el-table-column>
        <el-table-column prop="room" label="实验教室"></el-table-column>
      </el-table>
			
			<el-form-item>
				<DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'导 入'"></DialogButton>
			</el-form-item>
		</el-form>
	</el-dialog>
</template>

<script>
	import DialogButton from '../../../../components/DialogButton.vue'
  import XLSX from 'xlsx'
	export default {
		components: {
			DialogButton
		},
		methods: {
      readExcel(e){
        console.log(e)
        //表格导入
        const files = e.target.files;
        const fileReader = new FileReader();
        fileReader.onload = ev => {
          try {
            const data = ev.target.result;
            const workbook = XLSX.read(data, {
              type: "binary"
            });
            const wsname = workbook.SheetNames[0]; //取第一张表
            //console.log(workbook)
            const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
            this.toJson = ws// 转化成功的json
            //console.log(JSON.stringify(ws))
            //this.doChange()
          } catch (e) {
            return false;
          }
        };
        fileReader.readAsBinaryString(files[0]);
      },

			handleClose() {
				this.dialogVisible = false;
			},
			sumbitForm() {
				this.dialogVisible = false;
			},
			openDialog() {
				this.dialogVisible = true
			},
      //处理数据
      doChange(){
        var data = this.toJson ;
        var courseList = [];
        //1.遍历取出课程名
        for(let i = 0;i<data.length;i++){
          //判断课程数组中是否已存在该课程名
          let flag =false
          for(let j=0 ;j<courseList.length;j++){
            if(courseList[j].name === data[i].name){
              flag = true;
              break;
            }
          }
          //不存在则添加该课程
          if(!flag){
            let name = data[i].teacher;
              const course = {
                classes:data[i].class,
                name:data[i].name,
                teacher:{nickname:data[i].teacher},
                experiment: []
              }
              courseList.push(course);
          }

        }
        //2.完善课程信息
        for(let i = 0;i<data.length;i++){

          //把实验添加到对应的课程
          for(let j=0 ;j<courseList.length;j++){
            if(courseList[j].name === data[i].name){

              //初始化实验对象
              const experiment = {
                name: data[i].experiment,
                weak: data[i].number,
                classroom: {number: data[i].room},
                date: data[i].date
              };

              //添加实验到课程里
              courseList[j].experiment.push(experiment);


            }
          }
        }


        console.log(courseList)

        return courseList;

      },

			diaglogBtn(val) {
				switch (val) {
					case 'sure':
            this.$axios.post("/course/import",this.doChange()).then(res=>{
              this.$message(res.data.data);
              if(res.data.code == 200)
                this.$parent.reload()
            })
						break
					case 'reset':
						break
					case 'close':
						break
				}
				//console.log(val)
				this.dialogVisible = false;
			},
		},
		props: {

		},
		data() {
			return {
				dialogVisible: false,
        toJson:[{},{},{}],
			}
		}
	}
</script>

<style>
</style>
