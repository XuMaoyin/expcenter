<template>
	<el-dialog  :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
    <h2 v-text="notice.title" style="text-align: center"></h2>
    <div v-html="notice.content" style="min-height: 500px" class="ql-editor"></div>
	</el-dialog>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
	export default {
		components: {

		},
		methods: {
			handleClose() {
				this.dialogVisible = false;
			},

			openDialog() {

			  this.$axios.get("/notice/notice?id="+this.noticeId).then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
          this.notice = res.data.data ;
        })
				this.dialogVisible = true
			},

		},
		data() {
			return {
				dialogVisible: false,
        noticeId:0,
        notice:{}
			}
		}
	}
</script>

<style  scoped="scoped">
.ql-editor img{
    width: 50%;
}
</style>
