<template>
	<div class="layout-box">
		<el-card>
			<div class="e-tab">
				<div class="e-head-background">
					<div class="e-tab-head">
						<img src="https://gw.alicdn.com/tps/TB1W_X6OXXXXXcZXVXXXXXXXXXX-400-400.png" width="130px"
							height="130px" />
						<div class="e-head-infor">
							<span class="e-tag">用户名：永湖（12345）</span><br />
							<span class="e-tag">个性签名：热爱电影</span><br />
							<el-tag :key="tag" v-for="tag in dynamicTags" closable :disable-transitions="true"
								@close="handleClose(tag)">
								{{tag}}
							</el-tag>
							<el-select class="input-new-tag" v-if="inputVisible" v-model="inputValue" size="small"
								ref="saveTagInput" @change="handleInputConfirm">
								<el-option v-for="(item) in options" :key="item" :label="item" :value="item">
								</el-option>
							</el-select>
							<el-button v-else class="button-new-tag" size="small" @click="showInput">+ 添加
							</el-button>
						</div>
					</div>
					<div class="e-tab-link">
						<el-tooltip content="用户信息" placement="bottom" effect="light">
							<span @click="activeName='first'"><i class="el-icon-user"></i></span>
						</el-tooltip>
						<el-tooltip content="收藏" placement="bottom" effect="light">
							<span @click="activeName='second'"><i class="el-icon-star-on"></i></span>
						</el-tooltip>
						<el-tooltip content="评论" placement="bottom" effect="light">
							<span @click="activeName='third'"><i class="el-icon-s-comment"></i> </span>
						</el-tooltip>
						<el-tooltip content="评分" placement="bottom" effect="light">
							<span @click="activeName='fourth'"><i class="el-icon-star-off"></i> </span>
						</el-tooltip>
					</div>
				</div>

				<div class="e-tab-main">
					<el-tabs v-model="activeName" @tab-click="handleClick">
						<el-tab-pane name="first">
							<span slot="label"><i class="el-icon-user"></i> 用户信息</span>
							<div class="e-first">
								<el-form ref="form" :model="form">
									<el-form-item label="用户名" prop="name">
										<el-input v-model="form.name" :disabled="isxg"></el-input>
									</el-form-item>
									<el-form-item label="邮箱" prop="email" :rules="[
									      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
									      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
									    ]">
										<el-input v-model="form.email" :disabled="isxg"></el-input>
									</el-form-item>
									<el-form-item label="注册时间">
										<el-date-picker v-model="form.date" :disabled="isxg"></el-date-picker>
										(使用时间{{form.dateNum}}天)
									</el-form-item>
									<el-form-item label="个性签名">
										<el-input v-model="form.text" :disabled="isxg"></el-input>
									</el-form-item>
									<el-form-item>
										<el-button type="primary" @click="onSubmit('form')" :disabled="isxg">修改
										</el-button>
										<el-button @click="resetForm('form')">重置</el-button>
										<el-button @click="isxg=false" icon="el-icon-edit"></el-button>
									</el-form-item>
								</el-form>
							</div>
						</el-tab-pane>
						<el-tab-pane name="second">
							<span slot="label"><i class="el-icon-star-on"></i> 收藏</span>
							<div :class="{e_wsc:isjazai.ise_wsc}">
								收藏
								<el-card v-for="item,index in movieCollect" :key="index">
									<el-collapse accordion>
										<el-collapse-item>
											<template slot="title">
												<div class="e-item-infor">
													<el-image :src="item.photo"
														style="width: 100px; height: 120px;border: 1px solid #000000;float: left;"
														fit="fit">
														<div slot="error" class="image-slot">
															<i class="el-icon-picture-outline"></i>
														</div>
													</el-image>
													<div class="e-item-span">
														<span>电影名：{{item.name}}</span><br />
														<span>上映时间：{{item.showTime}}</span><br />
														<span>导演：{{item.director}}</span><br />
														<span>演员：{{item.actor}}</span><br />
														<span>类型：{{item.tag}}</span><br />
													</div>
													<div class="e-item-cz">
														<el-button icon="el-icon-share">查看</el-button>
														<el-button icon="el-icon-remove">取消收藏</el-button>
													</div>
												</div>
											</template>
											<el-card>
												<div class="e-infor">
													<h4>简介：</h4>
													<p>&nbsp;&nbsp;&nbsp;&nbsp;{{item.infor}}</p>
												</div>
											</el-card>
										</el-collapse-item>
									</el-collapse>
								</el-card>
							</div>
						</el-tab-pane>
						<el-tab-pane name="third">
							<span slot="label"><i class="el-icon-s-comment"></i> 评论</span>
							<div :class="{e_wpl:isjazai.ise_wpl}">
								评论
								<el-card v-for="item,index in movieCollect" :key="index">
									<el-collapse accordion>
										<el-collapse-item>
											<template slot="title">
												<div class="e-item-infor">
													<el-image :src="item.photo"
														style="width: 100px; height: 120px;border: 1px solid #000000;float: left;"
														fit="fit">
														<div slot="error" class="image-slot">
															<i class="el-icon-picture-outline"></i>
														</div>
													</el-image>
													<div class="e-item-span">
														<span>电影名：{{item.name}}</span><br />
														<span>上映时间：{{item.showTime}}</span><br />
														<span>导演：{{item.director}}</span><br />
														<span>演员：{{item.actor}}</span><br />
														<span>类型：{{item.tag}}</span><br />
													</div>
													<div class="e-item-cz">
														<el-button icon="el-icon-share">查看</el-button>
														<el-button icon="el-icon-remove">删除</el-button>
													</div>
												</div>
											</template>
											<el-card>
												<div class="e-infor">
													<h4>简介：</h4>
													<p>&nbsp;&nbsp;&nbsp;&nbsp;{{item.infor}}</p>
												</div>
											</el-card>
										</el-collapse-item>
									</el-collapse>
								</el-card>
							</div>
						</el-tab-pane>
						<el-tab-pane name="fourth">
							<span slot="label"><i class="el-icon-star-off"></i> 评分</span>
							<div :class="{e_wpf:isjazai.ise_wpf}">
								评分
								<el-card v-for="item,index in movieCollect" :key="index">
									<el-collapse accordion>
										<el-collapse-item>
											<template slot="title">
												<div class="e-item-infor">
													<el-image :src="item.photo"
														style="width: 100px; height: 120px;border: 1px solid #000000;float: left;"
														fit="fit">
														<div slot="error" class="image-slot">
															<i class="el-icon-picture-outline"></i>
														</div>
													</el-image>
													<div class="e-item-span">
														<span>电影名：{{item.name}}</span><br />
														<span>上映时间：{{item.showTime}}</span><br />
														<span>导演：{{item.director}}</span><br />
														<span>演员：{{item.actor}}</span><br />
														<span>类型：{{item.tag}}</span><br />
													</div>
													<div class="e-item-cz">
														<el-button icon="el-icon-share">查看</el-button>
														<el-button icon="el-icon-remove">删除</el-button>
													</div>
													<el-rate v-model="item.score" disabled :max="10" show-score
														text-color="#ff9900" score-template="{value}" :colors="colors"
														:allow-half="true">
													</el-rate>

												</div>
											</template>
											<el-card>
												<div class="e-infor">
													<h4>简介：</h4>
													<p>&nbsp;&nbsp;&nbsp;&nbsp;{{item.infor}}</p>
												</div>
											</el-card>
										</el-collapse-item>
									</el-collapse>

								</el-card>
							</div>
						</el-tab-pane>
					</el-tabs>
				</div>
			</div>

		</el-card>

	</div>
</template>
<script>
	export default {
		name: "PersonalCenter",
		data() {
			return {
				activeName: 'first',
				dynamicTags: ['动作', '喜剧', '爱情'],
				inputVisible: false,
				inputValue: '',
				options: ['恐怖', '动作', '喜剧', '爱情'],
				/* 表单 */
				isxg: true,
				form: {
					name: '永湖',
					emial: '',
					date: '2021-04-12',
					dateNum: '7',
					text: '',
				},
				/* 是否加载无数据 */
				isjazai: {
					ise_wsc: true,
					ise_wpl: true,
					ise_wpf: true,
				},
				colors: ['#99A9BF', '#F7BA2A',
					'#FF9900'
				], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
				/* 收藏影片 */
				movieCollect: [{
					name: "小人物",
					photo: '',
					infor: '这部影片',
					director: '王导',
					actor: '李严',
					showTime: '2021-01-04',
					tag: '喜剧',
					score: 3.5
				}, {
					name: "小人物",
					photo: '',
					infor: '这部影片',
					director: '王导',
					actor: '李严',
					showTime: '2021-01-04',
					tag: '喜剧',
					score: 5.5
				}]
			};
		},
		watch: {

		},
		methods: {
			handleClick(tab, event) {
				console.log(tab, event);
			},

			handleClose(tag) {
				this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
			},

			showInput() {
				this.inputVisible = true;
			},

			handleInputConfirm() {
				let inputValue = this.inputValue;
				if (inputValue) {
					if (this.dynamicTags.indexOf(inputValue) >= 0) {
						this.$message.error("已经存在标签");
					} else {
						this.dynamicTags.push(inputValue);
					}
				}
				this.inputVisible = false;
				this.inputValue = '';
			},
			onSubmit(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						alert('submit!');
						this.isxg = true;
					} else {
						alert('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},

		}
	};
</script>
<style>
	/* 样式覆盖 */
	.el-collapse-item__header {
		height: 20%;
		border-bottom: none;
	}

	.el-collapse {
		border-top: none;
		border-bottom: none;
	}

	.el-rate {
		height: 20px;
		line-height: 1;
		position: relative;
		top: -160px;
		left: 76%;
	}

	.el-collapse-item__wrap {
		width: 97%;
	}

	.layout-box {
		margin-top: 15px;
		background: #3481e6;
		padding: 10px 20px;
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		margin-left: 20px;
		margin-right: 10px;
	}

	.el-tag+.el-tag {
		margin-left: 10px;
	}

	.button-new-tag {
		margin-left: 10px;
		height: 32px;
		line-height: 30px;
		padding-top: 0;
		padding-bottom: 0;
		background-color: #000;
		color: #000;
		border: none;
	}

	.button-new-tag:hover {
		background-color: '';
		color: '';
		border: '';
	}

	.input-new-tag {
		width: 90px;
		margin-left: 10px;
		vertical-align: bottom;
	}

	.e-tab {
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		padding: 10px;
	}

	.e-head-background {
		background-color: #090723;
	}

	.e-tab-head {
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		padding: 20px 20px 0px;
	}

	.e-head-infor {
		display: inline-block;
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		padding: 10px;
		margin-left: 4%;
	}

	.e-head-infor .e-tag:nth-child(1) {
		font-size: 22px;
		color: #fff;
		line-height: 22px;
		display: flex;
		align-items: center;
	}

	.e-head-infor .e-tag {
		font-size: 12px;
		color: #a6a5ad;
		display: block;
		line-height: 1.2;
		margin-top: 15px;
	}

	.e-tab-link {
		position: fixed;
		right: 100px;
		top: 290px;
		z-index: 200;
	}

	.e-tab-link span {
		width: 50px;
		height: 50px;
		display: inline-block;
		vertical-align: middle;
		border-radius: 50%;
		background: #ed4630;
		text-align: center;
		line-height: 50px;
		font-size: 24px;
		color: #fff;
		margin-left: 15px;
		cursor: pointer;
		position: relative;
	}

	.e-tab-main {
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		padding: 30px;
		min-height: 400px;
	}

	.e_wsc {
		background: url(../assets/images/catu/暂无收藏.png) no-repeat;
		background-size: 30%;
		background-position: center;
		min-height: 400px;
	}

	.e_wpf {
		background: url(../assets/images/catu/暂无收藏.png) no-repeat;
		background-size: 30%;
		background-position: center;
		min-height: 400px;
	}

	.e_wpl {
		background: url(../assets/images/catu/暂无评论.png) no-repeat;
		background-size: 30%;
		background-position: center;
		min-height: 400px;
	}

	.e-item-infor {
	    width: 100%;
	    height: 150px;
	}

	.e-item-infor .e-item-span {
		font-size: 12px;
		color: #a6a5ad;
		display: block;
		line-height: 1.2;
		display: inline-block;
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		margin-left: 10px;
		line-height: 20px;
		padding: 10px;
		width: 90%;
	}

	.e-item-infor .e-item-span>span {
		margin-left: 20px;
	}

	.e-item-infor .e-item-span span:nth-child(1) {
		font-size: 22px;
		line-height: 22px;
		display: flex;
		align-items: center;
		color: #000000;
	}

	.e-item-cz {
		position: relative;
		top: -60px;
		left: 79%;
	}

	.e-infor {
		width: 90%;
	}
</style>
