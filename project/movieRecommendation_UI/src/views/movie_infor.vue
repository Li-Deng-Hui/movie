<template>
	<el-container>
		<el-header class="tab-bar" style="background-color: rgb(87, 130, 210);">
			<el-page-header @back="goBack" content="详情页面" style="height: 60px;line-height: 60px;color: #fff;">
			</el-page-header>
		</el-header>
		<el-container style="margin-top: 60px;">
			<el-main>
				<el-card>
					<div class="e-main">
						<el-row>
							<h1>电影名：{{movieInfor.name}}({{this.$route.params.movieId}})</h1>
						</el-row>
						<el-row :gutter="20">
							<el-col :span="5">
								<el-card :body-style="{ padding: '0px'}">
									<el-image fit="fill" style="width:100%;height:100%;" :src="movieInfor.photo">
										<!-- onerror="this.src='/src/assets/images/hotmovie/hot1.jpg'" -->
										<div slot="error" class="image-slot" style="height: 300px;">
											<i class="el-icon-picture-outline"></i>
										</div>
									</el-image>
									<span class="e-score">{{movieInfor.score}}</span>
								</el-card>
							</el-col>
							<el-col :span="12">
								<div>
									<div id="info">
										<span><span class="pl">导演</span>: <span class="attrs">殷若昕</span></span><br>
										<span><span class="pl">编剧</span>: <span class="attrs">游晓颖</span></span><br>
										<span><span class="pl">主演</span>: <span class="attrs">张子枫/</span></span><br>
										<span><span class="pl">类型:</span> <span class="attrs">剧情</span></span><br>
										<span class="pl">制片国家/地区:</span> 中国大陆<br>
										<span class="pl">语言:</span> 汉语普通话<br>
										<span class="pl">上映日期:</span> <span class="attrs">2021-04-02(中国大陆)</span><br>
										<span class="pl">片长:</span> <span class="attrs">127分钟</span><br>
										<span class="pl">豆瓣链接：</span> <span class="attrs"><a
												href="https://movie.douban.com/subject/35158160/?from=showing"
												rel="v:directedBy">https://movie.douban.com/subject/35158160/?from=showing</a></span><br>
									</div>
									<div class="e-pf" v-show="islog">
										<span>评分：
											<el-rate v-model="movieInfor.score" disabled :max="10" show-score
												text-color="#ff9900" score-template="{value}" :colors="colors"
												:allow-half="true">
											</el-rate>
										</span>
										<span>收藏：<i :class="[isStar?'el-icon-star-off':'el-icon-star-on']" @click="isStar=!isStar"></i></span>
									</div>
								</div>
							</el-col>
						</el-row>
						<el-row>
							<div>
								<h3>简介：</h3>
								<p>.........................</p>
							</div>
						</el-row>
					</div>
				</el-card>
				<el-card style="margin-top: 5px;">
					<h2>评论：</h2>
					<PingLong></PingLong>
				</el-card>
			</el-main>
			<el-aside width="400px">
				<el-card :body-style="{ padding: '0px'}" style="margin-top: 20px;">
					<div class="col-md-wide-25 pt-xs-0 main-right hidden-sm hidden-xs"
						style="position: fixed;top: 70px;width: 390px;left: 1130px;bottom: 0px;">
						<div class="pannel-box clearfix">
							<div class="box-title"><span class="iconfont icon-xin"></span>
								<h2>电影推荐</h2>
							</div>
							<ul class="txt-list txt-list-hot p-xs-0">
								<li><i>1</i><a href="/zhanzhengpian/jingangchuan/" title="金刚川"
										target="_blank">金刚川</a><span class="pull-right">HD1280高清国语中字版</span></li>
								<li><i>2</i><a href="/aiqingpian/sejie/" title="色戒" target="_blank">色戒</a><span
										class="pull-right"></span></li>
								<li><i>3</i><a href="/aiqingpian/yiluxiangxi/" title="一路向西"
										target="_blank">一路向西</a><span class="pull-right"></span></li>
								<li><i>4</i><a href="/zhanzhengpian/babai1/" title="八佰" target="_blank">八佰</a><span
										class="pull-right">超清</span></li>
								<li><i>5</i><a href="/juqingpian/3drouputuanjilebaojian/" title="3D肉蒲团极乐宝鉴"
										target="_blank" se_prerender_url="complete">3D肉蒲团极乐宝鉴</a><span
										class="pull-right"></span></li>
								<li><i>6</i><a href="/juqingpian/sanfutian/" title="三伏天" target="_blank">三伏天</a><span
										class="pull-right">超清</span></li>
								<li><i>7</i><a href="/kongbupian/anyu2/" title="黯欲" target="_blank">黯欲</a><span
										class="pull-right"></span></li>
								<li><i>8</i><a href="/xijupian/bameitu/" title="八美图" target="_blank">八美图</a><span
										class="pull-right">BD中字</span></li>
								<li><i>9</i><a href="/xijupian/muyuzhiwang/" title="沐浴之王" target="_blank">沐浴之王</a><span
										class="pull-right">超清</span></li>
								<li><i>10</i><a href="/juqingpian/yiluxiangxidongwandesenlin/" title="一路向西/东莞的森林"
										target="_blank" se_prerender_url="complete">一路向西/东莞的森林</a><span
										class="pull-right">BD中字</span></li>
							</ul>
						</div>
					</div>
				</el-card>
			</el-aside>
		</el-container>
	</el-container>

</template>

<script>
	import PingLong from '../components/pinglong.vue'
	export default {
		name: "MovieInfor",
		components: {
			PingLong
		},
		data() {
			return {
				currentDate: new Date(),
				movieID: this.$route.params.movieId,
				movieInfor: {
					name:'小人物',
					photo: '',
					score:8.8,
				},
				isStar:true,
				islog:true,
				colors: ['#99A9BF', '#F7BA2A',
					'#FF9900'
				], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
			};
		},
		methods: {
			goBack() {
				console.log('go back');
				this.$router.go(-1);
			},
			getMovieInfor() {
				this.$axios.get('douban/subject/' + movieID)
					.then((response) => {

					})
			}
		},
		mounted() {
			var movieId = this.$route.params.movieId;
			this.$message.info(movieId);
		},
	}
</script>

<style>
	.tab-bar {
		box-shadow: 0 2px 5px rgba(84, 80, 80, 0.44);
		position: fixed;
		margin-right: 4px;
		margin-left: 4px;
		top: 2px;
		z-index: 888;
		width: 100%;
		vertical-align: middle;
		-webkit-transition: .5s;
		-o-transition: .5s;
		-moz-transition: .5s;
		-ms-transition: .5s;
		transition: .5s;
		color: #fff;
	}

	.e-main {
		margin-top: 10px;
		margin-left: 20px;
	}

	.el-page-header__content {
		font-size: 18px;
		color: #f7f7f7;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}

	.image-slot {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 100%;
		background: #f5f7fa;
		color: #909399;
		font-size: 30px;
	}
	.e-score {
	    background-color: #ff0000ba;
	    color: #fff;
	    border-radius: 40%;
	    padding: 5px;
	    position: absolute;
	    left: 13px;
	    font-size: 14px;
	    margin: 2px;
	    box-shadow: 0px 1px 4px 0px #6f3d3d;
	    text-shadow: -2px 1px #a95d5d;
		}
		.e-pf{
			position: absolute;
			    top: 20px;
			    left: 70%;
		}
</style>
