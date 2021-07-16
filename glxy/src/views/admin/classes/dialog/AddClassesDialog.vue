<template>
	<el-dialog title="添加班级信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
		<el-form ref='addCourseForm' label-width="100px" v-model="form">

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
				this.dialogVisible = true
			},
			diaglogBtn(val) {
				switch (val) {
					case 'sure':

					  this.$axios.post("/class/add",this.form).then(res=>{
					    if(res.data.code == 500){
					      this.$message(res.data.message)
                return
              }
					    this.$parent.loadList();

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
		props: {

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
			}
		},
    mounted() {

		  //获取教师列表
		  this.$axios.get("/teacher/teachers").then(res=>{
		    if(res.data.code == 500){
		      this.$message(res.data.message)
          return
        }

		    this.teacherArray = res.data.data ;
      })
    }
  }
</script>

<style>
</style>
