<template>
	<div class="el-main">


		<el-form ref="applyEcperRef" :inline="true">
			<el-form-item label="自动审批">
				<el-switch v-model="auto" active-text="开" inactive-text="关" ></el-switch>
			</el-form-item>
			<el-form-item label="类型查找" class="inlineform">
				<el-select v-model="type" size="small">
					<el-option v-for="item in types" :key="item.value" :label="item.lable" :value="item.value"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="课程搜索" class="inlineform">
				<el-input v-model="coures" size="small" placeholder="请输入课程名称"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" size="small">搜索</el-button>
			</el-form-item>
		</el-form>


		<el-table :data="dataList" style="width: 100%;" :border="false">
			<el-table-column prop="type" label="调整类型">
				<template slot-scope="scope">
					<span v-if="scope.row.type=='insert'">添加</span>
					<span v-if="scope.row.type=='update'">修改</span>
					<span v-if="scope.row.type=='delete'">删除</span>
				</template>
			</el-table-column>
			<el-table-column prop="course" label="课程名称"></el-table-column>
			<el-table-column label="说明">
				<template slot-scope="scope">
					<p v-if="scope.row.type!='update'">{{scope.row.details}}</p>
					<el-popover trigger="click" placement="top" v-if="scope.row.type=='update'">
						<div slot="reference" class="name-wrapper">
							<el-tag size="medium">详细</el-tag>
						</div>
						<el-table :data="scope.row.details">
							<el-table-column label="修改前" align="center">
								<el-table-column prop="now.name" label="实验名称"></el-table-column>
								<el-table-column prop="now.room" label="实验室"></el-table-column>
								<el-table-column prop="now.weak" label="周次"></el-table-column>
								<el-table-column prop="now.weakday" label="周几"></el-table-column>
								<el-table-column prop="now.number" label="节次"></el-table-column>

							</el-table-column>
							<el-table-column label="修改后" align="center">
								<el-table-column prop="now.name" label="实验名称"></el-table-column>
								<el-table-column prop="now.room" label="实验室"></el-table-column>
								<el-table-column prop="now.weak" label="周次"></el-table-column>
								<el-table-column prop="now.weakday" label="周几"></el-table-column>
								<el-table-column prop="now.number" label="节次"></el-table-column>
							</el-table-column>
							<el-table-column  prop="details" label="备注"></el-table-column>
						</el-table>
					</el-popover>
				</template>
			</el-table-column>
			<el-table-column prop="status" label="状态" align="center"></el-table-column>
			<el-table-column label="操作" align="center" width="200">
				<template slot-scope="scope">
					<el-button size="small" type="warning" @click="pass(scope.row.id)">通过</el-button>
					<el-button size="small" type="danger" @click="refuse(scope.row.id)">回绝</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>

</template>

<script>
	export default {
		name: 'courseAdmin',
		components: {

		},
		methods: {

		},
		data() {
			return {
				dataList: [{
					type: 'insert',
					course: 'zhang',
					details: '添加一次实验',
					status: '审核中',
				}, {
					type: 'update',
					course: 'zhang',
					details: [{
						now: {
							room: 'xxx'
						},
						after: {
							room: 'yyyy'
						},
					}],
					status: '审核中',
				}],
				auto:true,
				type:'',
				coures:'',
				types:[{lable:'添加',value:'insert'},
				{lable:'更新',value:'update'},
				{lable:'删除',value:'delete'},
				]
				

			}
		}
	}
</script>

<style>
.inlineform{
	margin-left: 100px;
}
</style>
