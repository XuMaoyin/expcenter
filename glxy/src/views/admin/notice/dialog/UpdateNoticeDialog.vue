<template>
	<el-dialog title="更新公告" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
		<el-form ref='updateFormRef' label-width="100px" >

			<el-form-item label="标题">
				<el-input v-model="form.title"></el-input>
			</el-form-item>
			<el-form-item label="内容">
        <quill-editor v-model="form.content" :options="editorOption" @blur="onEditorBlur($event)"
                      @focus="onEditorFocus($event)" @change="onEditorChange($event)">
        </quill-editor>
			</el-form-item>
			
			<el-form-item>
				<DialogButton @diaglogBtn="diaglogBtn" :reset="true" :sure="true" :sureName="'修 改'"></DialogButton>
			</el-form-item>


		</el-form>
	</el-dialog>
</template>

<script>
	import DialogButton from '../../../../components/DialogButton.vue'
  import {quillEditor} from "vue-quill-editor";
	export default {
		components: {
			DialogButton,
      quillEditor,
		},
    computed: {
      editor() {
        return this.$refs.myQuillEditor.quill
      }
    },
		methods: {
      onEditorBlur(editor) { //失去焦点事件
      },
      onEditorFocus(editor) { //获得焦点事件
      },
      onEditorChange({editor, html, text}) { //编辑器文本发生变化
        //this.form.content可以实时获取到当前编辑器内的文本内容
        console.log(this.form.content);
      },
      reset(){
        this.form.title = '';
        this.form.content = '';
      },
			handleClose() {
				this.dialogVisible = false;
			},
			sumbitForm() {
				this.dialogVisible = false;
			},
			openDialog() {
        this.$axios.get("/notice/notice?id="+this.noticeId).then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
          this.form = res.data.data ;
        })
				this.dialogVisible = true
			},
			diaglogBtn(val) {
				switch (val) {
					case 'sure':
            this.$axios.post("/notice/update",this.form).then(res=> {
              if (res.data.code == 500) {
                this.$message(res.data.message)
                return
              }

              this.reset()
            })
						break
					case 'reset':
					  this.reset()
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
				  title:'',
          content:'',
        },
        noticeId:0,
        editorOption:{},
			}
		}
	}
</script>

<style>
</style>
