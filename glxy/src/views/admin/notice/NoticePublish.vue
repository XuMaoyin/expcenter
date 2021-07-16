<template>
	<div class="el-main">
		<el-form v-model="form" ref="addNoticeRef">
			<el-form-item label="标题">
				<el-input v-model="form.title"></el-input>
			</el-form-item>
			
			<el-form-item label="正文">
				<quill-editor v-model="form.content" :options="editorOption" @blur="onEditorBlur($event)"
					@focus="onEditorFocus($event)" @change="onEditorChange($event)">
				</quill-editor>

			</el-form-item>
			<el-form-item>
				<el-button type="warning" @click="reset">重置</el-button>
				<el-button type="primary" @click="publish">发布</el-button>
			</el-form-item>
		</el-form>
	</div>

</template>

<script>
	import {quillEditor,Quill} from 'vue-quill-editor'
  import {container, ImageExtend, QuillWatch} from 'quill-image-extend-module'
	import 'quill/dist/quill.core.css'
	import 'quill/dist/quill.snow.css'
	import 'quill/dist/quill.bubble.css'
  Quill.register('modules/ImageExtend', ImageExtend)

	export default {
		components: {

			quillEditor,
		},
		methods: {
			onEditorBlur(editor) { //失去焦点事件 
			},
			onEditorFocus(editor) { //获得焦点事件
			},
			onEditorChange({editor, html, text}) { //编辑器文本发生变化
				//this.form.content可以实时获取到当前编辑器内的文本内容
				//console.log(this.form.content);
			},
      reset(){
			  this.form.title = '';
			  this.form.content = '';
      },
      publish(){
			  this.$axios.post("/notice/add",this.form).then(res=>{
			    if(res.data.code == 500){
			      this.$message(res.data.message)
            return
          }

			    this.reset();

        })
      }
		},
		computed: {
			editor() {
				return this.$refs.myQuillEditor.quill
			}
		},
		data() {
			return {
			  form:{
          title:'',
          content:'',
        },
				editorOption: {
          modules: {
            ImageExtend: {
              loading: true,
              name: 'img',
              headers:(xhr)=>{

              },
              //action: '/api/notice/upload',
              action: null,
              response: (res) => {
                console.log(res);
                return res.data;
              }
            },
            toolbar: {
              container: container,
              handlers: {
                'image': function () {
                  QuillWatch.emit(this.quill.id)
                }
              }
            }
          }
        },
			}
		}
	}
</script>

<style>
.quill-editor {
  height: 350px;
  margin: 50px 0;
 }
</style>
