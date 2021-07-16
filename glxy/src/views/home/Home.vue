<template>
	<el-container>
	  <el-header style="height: 110px;">
		  	<img src="../../assets/header-backstage.png" />
		  	<div style="text-align: right;margin-right: 50px;">
		  		<span style="margin-right: 30px;">版本：v1.0</span>
          <span>欢迎您，{{username}}&nbsp;&nbsp;&nbsp;</span>
		  		<a href="#" style="color: orange;" @click="logout">退出</a>
		  	</div>
		  
	  </el-header>
	  <el-container>
	    <el-aside width="200px">
			<Nav></Nav>
		</el-aside>
	    <el-main>
			<router-view></router-view>
		</el-main>
	  </el-container>
	</el-container>
	
</template>

<script>
	import Nav from './Nav.vue'
	export default {
		components:{
			Nav,
		},
		data() {
			return {
				username:'',
			};
		},
		methods: {
			logout(){
				sessionStorage.setItem('user','')
				sessionStorage.setItem('identity','')
				//console.log(sessionStorage.getItem('user'))
        this.$router.push('/login')

			}
		},
		mounted() {
      let identity =  sessionStorage.getItem("identity")
      if(identity == 'admin'){
        this.username = '管理员';
      }else{
        let userString = sessionStorage.getItem('user')
        let user =  JSON.parse(userString)
        this.username = user.nickname
        //console.log(user)
      }
		}
	}
</script>

<style>
	.el-header, .el-footer {
	    background-color: #3A8EE6;
	    color: white;
	  }
	  
	  .el-aside {
	    height: 700px;
	    color: #333;
	    
	    
	  }

  .el-menu-item-group {
    height: 50px;
  }
	  
	  .el-main {
	   
	    color: #333
	  }
	  
	  body > .el-container {
	    margin-bottom: 40px;
	  }
	  
	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }
	  
	  .el-container:nth-child(7) .el-aside {
	    line-height: 200px;
	  }
</style>
