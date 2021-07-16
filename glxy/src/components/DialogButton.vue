<template>
	<div class="button-right">
		<el-button v-if="sure" type="primary" @click="$emit('diaglogBtn', 'sure') ">{{sureName}}</el-button>
		<el-button v-if="reset" type="warning" @click="$emit('diaglogBtn', 'reset')">重置</el-button>
		<el-button v-if="close" @click="$emit('diaglogBtn', 'close')">取消</el-button>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				
			}
		},
		props:{
			sure:{type:Boolean,default:false},
			reset:{type:Boolean,default:false},
			close:{type:Boolean,default:true},
			sureName:{type:String,default:'确 定'},
		},
		methods:{
			
		}
	}
</script>

<style type="text/css">
	.button-right{
		margin-top: 5px;
		float: right;
		margin-right: 2.5rem;
		
	}
</style>
