<template>
	<div >
		<div class="headButton">
			<el-button type="primary" size="small">批量置顶</el-button>
			<el-button type="primary" size="small">批量删除</el-button>
			
		</div>
		<div>
			<el-table :data="showList" style="width: 100%;" height="510px">
				<el-table-column prop="title" label="标题"></el-table-column>
				<el-table-column prop="date" label="发布时间"></el-table-column>
<!--				<el-table-column prop="author" label="发布者"></el-table-column>-->
				<el-table-column prop="hits" label="点击量"></el-table-column>
				<el-table-column label="置顶">
					<template slot-scope="scope">
						<el-button v-if="scope.row.topTime  === scope.row.datetime"  size="small" type="text" @click="top(scope.row.id)">置顶</el-button>
						<el-button v-if="scope.row.topTime  != scope.row.datetime"  size="small" type="text" @click="unTop(scope.row.id)">取消置顶</el-button>

					</template>
				</el-table-column>
				<el-table-column label="操作" align="center" >
					<template slot-scope="scope">
						
						<el-button size="small" plain type="primary" @click="preview(scope.row.id)">预览</el-button>
						<el-button size="small" @click="update(scope.row.id)">修改</el-button>
						<el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
    <div style="text-align: center;margin-top: 10px">
      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="currentChange"
          :page-size="page.pageSize"
          :current-page="page.currentPage"
          :total="page.total">
      </el-pagination>
    </div>
		<div class="el-dialog">
			<update-notice-dialog ref="updateNoticeRef"></update-notice-dialog>
			<preview-notice-dialog ref="previewRef"></preview-notice-dialog>
		</div>
	</div>

</template>

<script>
		
	import UpdateNoticeDialog from './dialog/UpdateNoticeDialog.vue'
	import PreviewNoticeDialog from './dialog/PreviewNoticeDialog.vue'
	

	export default {
		name: 'courseAdmin',
		components: {
			
			UpdateNoticeDialog,
			PreviewNoticeDialog
		},
		methods: {
			preview(id) {
        this.$refs.previewRef.noticeId = id;
				this.$refs.previewRef.openDialog();
			},
			top(id){
        this.axios.get("/notice/top?id="+id).then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
          this.loadList()
        })
			},
      unTop(id){
        this.axios.get("/notice/unTop?id="+id).then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
          this.loadList()
        })
      },
			update(id) {
        this.$refs.updateNoticeRef.noticeId = id ;
				this.$refs.updateNoticeRef.openDialog();
			},
			del(id){
				this.$confirm('此操作将永久删除, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
				          this.$axios.get("/notice/del?id="+id).then(res=>{
				            if(res.data.code ==200 ){
				              this.$message("删除成功")
                      this.loadList();
                    }
                  })
				        }).catch(() => {
				          this.$message({
				            type: 'info',
				            message: '已取消删除'
				          });          
				        });
			},
      loadList(){
			  this.axios.get("/notice/notices").then(res=>{
          if(res.data.code == 500){
            this.$message(res.data.message)
            return
          }
          this.dataList = res.data.data ;
          this.page.total = this.dataList.length;
          this.currentChange(1)
        })
      },
      currentChange(val){
        const end = val * this.page.pageSize;
        const start = (val - 1) * this.page.pageSize;
        this.showList = this.dataList.slice(start,end);
      }
		}

		,
		data() {
			return {
			  showList:[],
				dataList: [{
					title:'2020-2021第二学期实验中心正式开始运转',
					pubtime:'2021-03-26',
					author:'管理员',
					hits:666,
					top:3,
					id:'0001'
				}],
        page:{
          total:0,
          pageSize:8,
          currentPage:1,
        }

			}
		},
    mounted() {
		  this.loadList()
    }
  }
</script>

<style>
	.headButton {
		margin: 10px auto;
	}
</style>
