<template>
	<el-dialog title="添加实验室" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
		<el-form ref='addCourseForm' label-width="100px" v-model="form">

			<el-form-item label="实验室名称">
				<el-input v-model="form.roomName"></el-input>
			</el-form-item>
			<el-form-item label="实验室编号">
				<el-input v-model="form.number"></el-input>
			</el-form-item>
			<el-form-item label="负责人">
				<el-select v-model="form.leaderId" filterable placeholder="请选择">
					<el-option v-for="item in teacherArray" :key='item.id' :value="item.id" :label="item.nickname">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="实验室描述">
				<el-input v-model="form.desc" type="textarea"></el-input>
			</el-form-item>
			<el-form-item label="可容纳人数">
				<el-input v-model="form.people"></el-input>
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

            this.$axios.post("/room/add",this.form).then(res=>{
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
          roomName: "",
          number: '',
          desc: '',
          people: 0,
          count: 0,
          leaderId:0,
        },
        teacherArray:[],
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
