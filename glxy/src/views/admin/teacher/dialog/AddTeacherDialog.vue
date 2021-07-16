<template>
	<el-dialog title="添加教师信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
		<el-form ref='addCourseForm' v-model="form" label-width="60px">
			<el-form-item label="姓名">
				<el-input v-model="form.nickname"></el-input>
			</el-form-item>
			<el-form-item label="性别">
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
			</el-form-item>
			<el-form-item label="部门">
				<el-select v-model="form.department" filterable placeholder="请选择">
					<el-option v-for="item in departments" :key='item.label' :value="item.value" :label="item.value">
					</el-option>
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
              this.axios.post("/teacher/add",this.form).then(res=>{
                if(res.data.code == 500){
                  this.$message(res.data.message)
                  return
                }
                this.$parent.loadList();
                this.form.name = '';
                this.dialogVisible = false ;

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
				  username:'',
          password:'123456',
          name: '',
          sex: '男',
          department: '',
        },

				departments: [
				    {label: '1', value: '行政部'},
				    {label: '2', value: '党政部'},
				    {label: '3', value: '市场营销系'},
				    {label: '4', value: '会计学系'},
				    {label: '5', value: '电子商务系'},
				    {label: '6', value: '工商管理系'},
				    {label: '7', value: '国际经济与贸易系'},
				    {label: '8', value: '公共事业管理系'},
				    {label: '9', value: '实验中心'},
				],

			}
		}
	}
</script>

<style>
</style>
