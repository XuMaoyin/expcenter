<template>
  <div>
    <div style="width: 100%;text-align: center">
      <h1>404   Not Found</h1>
      <hr>
      页面走丢了,或许他并不存在，或许他一直都在
      <br>
      <br>
      <a href="javascript:void(0)" @click="goBefore">返回上一页</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "",
  methods: {
    goBefore() {
      this.$router.go(-1)
    },
    reLogin() {
      sessionStorage.clear();
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>