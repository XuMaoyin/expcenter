<template>
	<el-dialog title="更新班级信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
		<el-form ref='updateClassesForm' label-width="100px" v-model="form">

      <el-form-item label="班级名称">
        <el-input v-model="form.className"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="人数">
        <el-input-number v-model="form.people"></el-input-number>
      </el-form-item>
      <el-form-item label="班主任">
        <el-select v-model="form.leaderId" filterable  >
          <el-option v-for="item in teacherArray" :key="item.id" :value="item.id" :label="item.nickname"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'添 加'"></DialogButton>
      </el-form-item>

		</el-form>
	</el-dialog>
</template>

<script>
	import DialogButton from '../../../../components/DialogButton.vue'
	export default {
		components: {
			DialogButton
		},
		methods: {
			handleClose() {
				this.dialogVisible = false;
			},
			sumbitForm() {
				this.dialogVisible = false;
			},
			openDialog() {
			  this.$axios.get("/class/class?id="+this.classId).then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
			    this.form = res.data.data;
        })

        //获取教师列表
        this.$axios.get("/teacher/teachers").then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }

          this.teacherArray = res.data.data ;
        })

				this.dialogVisible = true
			},
			diaglogBtn(val) {
				switch (val) {
					case 'sure':
						this.$axios.post("/class/update",this.form).then(res=>{
              if(res.data.code == 500){
                this.$message(res.data.message)
                return
              }

            })

						break
					case 'reset':
						break
					case 'close':
						break
				}
				console.log(val)
				this.dialogVisible = false;
			}
		},
		data() {
			return {
        dialogVisible: false,
        form:{
          className:'',
          desc: '',
          people: 0,
          leaderId:'',
        },
        teacherArray:[
          {id:0,nickname:'小王'}
        ],
        classId:0
			}
		}
	}
</script>

<style>
</style>
