<template>

	<div class="el-container">
		<el-form v-model="ruleForm2"  status-icon ref="ruleForm2" label-position="left" label-width="0px"
			class="demo-ruleForm login-page">
			<h3 class="title">系统登录</h3>
			<el-form-item prop="username">
				<el-input type="text" v-model="ruleForm2.username"  auto-complete="off" placeholder="用户名"></el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="密码"></el-input>
			</el-form-item>
      <el-form-item>
        <el-radio v-model="ruleForm2.identity" label="student">社团</el-radio>
        <el-radio v-model="ruleForm2.identity" label="teacher" >教师</el-radio>
        <el-radio v-model="ruleForm2.identity" label="admin">管理员</el-radio>
      </el-form-item>
			<el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>
			<el-form-item style="width:100%;">
				<el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
			</el-form-item>
		</el-form>
	</div>


</template>

<script>
	export default {
		data() {
			return {
				logining: false,
				ruleForm2: {
					username: 'admin',
					password: '123456',
          identity:'admin'
				},
				rules2: {
					username: [{
						required: true,
						message: 'please enter your account',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: 'enter your password',
						trigger: 'blur'
					}],
          identity:[{
            required: true,
            message: '请选择登录身份',
            trigger: 'submit'
          }]
				},
				checked: false
			}
		},
		methods: {
			handleSubmit() {
				this.logining = true;
				this.$axios.post('home/login',this.ruleForm2).then(res => {


				  if(res.data.code == 200){
            sessionStorage.setItem('user',JSON.stringify(res.data.data)  )
            sessionStorage.setItem("identity",this.ruleForm2.identity)
            this.$router.push('/'+this.ruleForm2.identity+'/info')
          }else{
				    this.$alert(res.data.message)
            this.logining =false;
				    return
          }

					console.log(res.data)
				}).catch(error=>{
					console.log(error)
				})
			}
		}
	};
</script>

<style scoped>
	.el-container {
		width: 100%;
		height: 100%;
		background: url(../../assets/bg.jpg) no-repeat;
		background-size: 100% 100%;
	}

	.login-page {
		-webkit-border-radius: 5px;
		border-radius: 5px;
		margin: 180px auto;
		width: 350px;
		padding: 35px 35px 15px;
		background: rgba(250,250,250,0.7);
		border: 1px solid #eaeaea;
		box-shadow: 0 0 25px #cac6c6;
	}

	label.el-checkbox.rememberme {
		margin: 0px 0px 15px;
		text-align: left;
	}
</style>
