<template>
  <div class="el-main">
    <div class="headButton">
      <el-button @click="addRoom" type="primary" size="small">添加实验室</el-button>

    </div>
    <div>
      <el-table :data="dataList" style="width: 100%;" :border="false">

        <el-table-column prop="roomName" label="实验室名称"></el-table-column>
        <el-table-column prop="number" label="实验室编号"></el-table-column>
        <el-table-column prop="desc" label="实验室描述"></el-table-column>
        <el-table-column prop="people" label="可容纳人数"></el-table-column>
        <el-table-column prop="teacher.nickname" label="负责人"></el-table-column>
        <el-table-column label="本期使用统计">
          <template slot-scope="scope">
            <el-popover placement="bottom" trigger="click">
              <div slot="reference">
                <el-tag>{{ scope.row.count }}次实验</el-tag>
              </div>
              <el-table :data="scope.row.experiments">
                <el-table-column prop="experimentName" label="实验名称"></el-table-column>
                <el-table-column prop="experimentClass" label="实验班级"></el-table-column>
                <el-table-column prop="experimentTeacher" label="任课老师"></el-table-column>
                <el-table-column prop="experimentPeoples" label="实验人数"></el-table-column>
              </el-table>

            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="250">
          <template slot-scope="scope">
            <el-button size="small" @click="update(scope.row.id)">修改</el-button>
            <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="el-dialog">
      <add-room-dialog ref="addRoomRef"></add-room-dialog>
      <update-room-dialog ref="updateRoomRef"></update-room-dialog>

    </div>
  </div>

</template>

<script>
import AddRoomDialog from './dialog/AddRoomDialog.vue'
import UpdateRoomDialog from './dialog/UpdateRoomDialog.vue'


export default {
  name: 'experimentAdmin',
  components: {
    AddRoomDialog,
    UpdateRoomDialog,

  },
  methods: {
    loadList() {
      this.$axios.get("/room/rooms").then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message);
          return
        }
        this.dataList = res.data.data;

      })
    },

    addRoom() {
      this.$refs.addRoomRef.openDialog();

    },

    update(id) {
      this.$refs.updateRoomRef.roomId = id;
      this.$refs.updateRoomRef.openDialog();
    },
    del(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/room/del?id=" + id).then(res => {
          if (res.data.code == 500) {
            this.$message(res.data.message)
            return
          }
          this.loadList();
        })
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    mounted() {
      this.loadList()
    },
    data() {
      return {
        dataList: [{
          roomName: "沙盘模拟实验室",
          number: '30903',
          desc: '主用于沙盘模拟实验的开放实验室',
          people: '99',
          count: 0,
          teacher: {id: 0, nickname: 555},
          experiments: [
            {experimentName: 'xxxx', experimentClass: '电商171', experimentPeoples: 65, experimentTeacher: '马化腾'}
          ]

        }],

      }
    }
  }
</script>

<style>
.headButton {
  margin: 10px auto;
}
</style>
