<template>
    <div id="movie-schedule">
      <!--搜索-->
      <div class="top">
        <el-col :span="12">
          <el-input placeholder="请输入内容" v-model="input" class="input-with-select" style="width: 100%">
            <el-button slot="append" icon="el-icon-search" @click="search">搜索</el-button>
          </el-input>
        </el-col>
        <el-col :span="2" :offset="1">
          <el-button type="primary" size="small" @click="addSchedule">安排电影</el-button>
        </el-col>
      </div>
      <!--表格-->
      <div class="schedule-table">
        <el-table
          border
          :data="tableData"
          style="width: 100%">
          <el-table-column
            label="排片 ID"
            align="center"
            width="100"
            prop="schedule_id">
          </el-table-column>
          <el-table-column
            label="电影名"
            show-overflow-tooltip
            prop="name">
          </el-table-column>
          <el-table-column
            label="影院名"
            show-overflow-tooltip
            prop="cinema_name">
          </el-table-column>
          <el-table-column
            label="放映大厅"
            prop="hall_name">
          </el-table-column>
          <el-table-column
            label="放映日期"
            prop="show_date">
          </el-table-column>
          <el-table-column
            label="放映时间"
            prop="show_time">
          </el-table-column>
          <el-table-column
            label="售价（元）"
            prop="price">
          </el-table-column>
          <el-table-column 
          width="100"
          align="center"
          label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">撤销</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--分页-->
      <div class="block" v-if="tableData.length">
        <el-pagination
          @current-change="currentChange"
          background
          layout="prev, pager, next"
          :page-size="8"
          :total="total">
        </el-pagination>
      </div>
      <!--添加排片-->
      <div>
        <el-dialog
          title="添加排片"
          :visible.sync="addDialogFormVisible"
          :modal-append-to-body="false"
          v-if="addDialogFormVisible"
          :show-close="false"
        >
          <el-form :label-position="labelPosition" :rules="rules" label-width="80px" :model="scheduleInfo">
            <el-form-item label="电影名" prop="movieValue">
              <el-col :span="12">
                <el-select v-model="scheduleInfo.movieValue" placeholder="请选择" @change="changeMovie" style="width: 100%">
                  <el-option
                    v-for="item in movieOptions"
                    :key="item.movie_id"
                    :label="item.name"
                    :value="item.movie_id">
                  </el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="影院" prop="cinemaValue">
              <el-col :span="12">
                <el-select v-model="scheduleInfo.cinemaValue" placeholder="请选择" @change="changeCinema" style="width: 100%">
                  <el-option
                    v-for="item in cinemaOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    >
                  </el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="影厅" prop="hallValue">
              <el-col :span="12">
                <el-select v-model="scheduleInfo.hallValue" placeholder="请选择" style="width: 100%">
                  <el-option
                    v-for="item in hallOptions"
                    :key="item.hall_id"
                    :label="item.name"
                    :value="item.name">
                  </el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="上映日期" prop="public_date">
              <el-col :span="12">
                <el-date-picker
                  v-model="moviePublicDate"
                  type="date"
                  readonly
                  style="width: 100%"
                >
                </el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="放映日期" prop="movieShowDate">
              <el-col :span="12">
                <el-date-picker
                  v-model="scheduleInfo.movieShowDate"
                  type="date"
                  placeholder="选择日期"
                  :picker-options="datePickerOptions"
                  value-format="yyyy-M-d"
                  @change="movieShowDateChange"
                  style="width: 100%"
                >
                </el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="放映时间" prop="movieShowTime">
              <el-col :span="12">
                <el-select v-model="scheduleInfo.movieShowTime" placeholder="请选择时间" style="width: 100%">
                  <i slot="prefix" class="el-input__icon el-icon-time"></i>
                  <el-option
                    v-for="item in timeOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="售价" prop="price">
              <el-col :span="12">
                <el-input v-model="scheduleInfo.price" style="width: 100%"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="addDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addScheduleInfo">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
</template>

<script>
    export default {
        name: "MovieSchedule",
        data() {
          let checkPrice = (rule, value, callback) => {
            if (!value) {
              callback(new Error('售价不能为空'));
            } else {
              if (/((^[1-9]\d*)|^0)(\.\d{0,2}){0,1}$/.test(value)){
                callback();
              } else{
                callback(new Error('请输入正确格式的售价'));
              }
            }
          };
          return {
            tableData:[],
            total:0,
            currentPage:1,
            scheduleInfo:{},
            dialogFormVisible:false,
            addDialogFormVisible:false,
            dialogTitle:'',
            labelPosition:'right',
            input:'',
            searchInput:'',
            rules: {
              movieValue: [
                { required: true, message: '电影名不能为空', trigger: 'change' },
              ],
              cinemaValue: [
                { required: true, message: '影院名不能为空', trigger: 'change' },
              ],
              hallValue: [
                { required: true, message: '影厅名不能为空', trigger: 'change' },
              ],
              movieShowDate: [
                { required: true, message: '放映日期不能为空', trigger: 'change' },
              ],
              movieShowTime: [
                { required: true, message: '放映时间不能为空', trigger: 'change' },
              ],
              price: [
                { required: true, message: '售价不能为空', trigger: 'blur' },
                { validator: checkPrice, trigger: 'blur' }
              ]
            },

            movieOptions: [],

            cinemaOptions: [],

            hallOptions: [],

            timeOptions:[],

            moviePublicDate:'',

            datePickerOptions:{
              disabledDate: this.disabledDate
            }
          }
        },
        created(){
          this.loadCurrentPageMovieSchedule(this.currentPage,8,'');
        },
        methods: {
        
          handleEdit(index, row) {
            console.log(index, row);
          },
        
          //搜索电影
          search(){
            this.searchInput = this.input;
            this.loadCurrentPageMovieSchedule(1,8,this.searchInput);
          },
         
          //选择电影
          changeMovie(value){
            if (value){
              this.movieOptions.forEach((val)=>{
                if (val.movie_id===value){
                  this.moviePublicDate = val.public_date;
                }
              })
            }
          },
        
          //限制可选日期
          disabledDate(time){
            if (this.scheduleInfo.movieValue){
              if (new Date(this.moviePublicDate)-new Date()>0){
                return time.getTime() < new Date(this.moviePublicDate);
              } else{
                return time.getTime() < new Date()
              }
            } else{
              return time.getTime() > 0;
            }
          },
        },
    }
</script>

<style>
  .schedule-table{
    width:90%;
    min-width:900px;
    margin: 0 auto;
    margin-bottom: 30px;
  }
  .top{
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 30px 0;
    margin-bottom: 30px;
  }
  .block{
    text-align: center;
  }
  .el-dialog__header{
    text-align: center;
  }
  .el-dialog__body .el-form-item{
    padding-left: 20%;
  }
</style>
