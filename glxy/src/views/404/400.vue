<template>
  <div>
    <div style="width: 100%;text-align: center">
      <h1>400 Insufficient Permissions</h1>
      <hr>
      这儿貌似是一些不该你看到的内容呢，很抱歉不能展示给你QAQ
      <br>
      <br>
      你可以
      <a href="javascript:void(0)" @click="goBefore">返回上一页</a>
      或者
      <a href="javascript:void(0)" @click="reLogin">换个身份登录
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "",
  methods:{
    goBefore(){
      this.$router.go(-1)
    },
    reLogin(){
      sessionStorage.clear();
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>