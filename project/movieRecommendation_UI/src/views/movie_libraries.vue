<template>
	<div class="layout-box">
		<el-card>
			<div class="type-box clearfix">
				<div class="type-title bottom-line">
					<span class="pull-left" id="conreset"><a href="/zongyi/index_____.html" data="id-4">重新筛选</a></span>
					<span class="pull-right slideDown-btn" id="slideDown-box" @click="show=!show">{{show?'收起':'展开'}}<i class="iconfont" :class="show?'icon-up':'icon-down'"></i></span>
				</div>
				<div class="slideDown-box" v-show="show">
					<div class="type-select swiper-container-horizontal swiper-container-free-mode" id="mcat-select">
						<ul class="swiper-wrapper" id="mcid"
							style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
							<li class="swiper-slide swiper-slide-active"><a class="text-muted">类型</a></li>
							<li class="swiper-slide swiper-slide-next"><a href="/zongyi/index_____addtime.html"
									data="mcid-0">全部</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_zhenrenxiu____addtime.html"
									data="mcid-179">真人秀</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_tuokouxiu____addtime.html"
									data="mcid-176">脱口秀</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_xuanxiu____addtime.html"
									data="mcid-182">选秀</a>
							</li>
							<li class="swiper-slide active"><a href="/zongyi/index_qinggan____addtime.html"
									data="mcid-175">情感</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_fangtan____addtime.html"
									data="mcid-180">访谈</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_meishi____addtime.html"
									data="mcid-181">美食</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_lvyou____addtime.html"
									data="mcid-186">旅游</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_caijing____addtime.html"
									data="mcid-185">财经</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_junshi____addtime.html"
									data="mcid-194">军事</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_zhichang____addtime.html"
									data="mcid-187">职场</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_yinle____addtime.html"
									data="mcid-178">音乐</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_shishang____addtime.html"
									data="mcid-177">时尚</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_yule____addtime.html" data="mcid-172">娱乐</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_tiyu____addtime.html" data="mcid-200">体育</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_shenghuo____addtime.html"
									data="mcid-188">生活</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_shehui____addtime.html"
									data="mcid-189">社会</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_xinwen____addtime.html"
									data="mcid-190">新闻</a>
							</li>
							<li class="swiper-slide"><a href="/zongyi/index_kandian____addtime.html"
									data="mcid-195">看点</a>
							</li>

						</ul><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
					</div>
					<div class="type-select swiper-container-horizontal swiper-container-free-mode" id="area-select">
						<ul class="swiper-wrapper" id="area"
							style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
							<li class="swiper-slide swiper-slide-active"><a class="text-muted">地区</a></li>
							<li class="swiper-slide active swiper-slide-next"><a
									href="/zongyi/index_qinggan____addtime.html" data="area-0">全部</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_dalu___addtime.html"
									data="area-%E5%A4%A7%E9%99%86">大陆</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_xianggang___addtime.html"
									data="area-%E9%A6%99%E6%B8%AF">香港</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_taiwan___addtime.html"
									data="area-%E5%8F%B0%E6%B9%BE">台湾</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_meiguo___addtime.html"
									data="area-%E7%BE%8E%E5%9B%BD">美国</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_hanguo___addtime.html"
									data="area-%E9%9F%A9%E5%9B%BD">韩国</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_riben___addtime.html"
									data="area-%E6%97%A5%E6%9C%AC">日本</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_taiguo___addtime.html"
									data="area-%E6%B3%B0%E5%9B%BD">泰国</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_xinjiapo___addtime.html"
									data="area-%E6%96%B0%E5%8A%A0%E5%9D%A1">新加坡</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_malaixiya___addtime.html"
									data="area-%E9%A9%AC%E6%9D%A5%E8%A5%BF%E4%BA%9A">马来西亚</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_yindu___addtime.html"
									data="area-%E5%8D%B0%E5%BA%A6">印度</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_yingguo___addtime.html"
									data="area-%E8%8B%B1%E5%9B%BD">英国</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_faguo___addtime.html"
									data="area-%E6%B3%95%E5%9B%BD">法国</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_jianada___addtime.html"
									data="area-%E5%8A%A0%E6%8B%BF%E5%A4%A7">加拿大</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_xibanya___addtime.html"
									data="area-%E8%A5%BF%E7%8F%AD%E7%89%99">西班牙</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_eluosi___addtime.html"
									data="area-%E4%BF%84%E7%BD%97%E6%96%AF">俄罗斯</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan_qita___addtime.html"
									data="area-%E5%85%B6%E5%AE%83">其它</a></li>
						</ul><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
					</div>
					<div class="type-select swiper-container-horizontal swiper-container-free-mode" id="year-select">
						<ul class="swiper-wrapper" id="year"
							style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
							<li class="swiper-slide swiper-slide-active"><a class="text-muted">年代</a></li>
							<li class="swiper-slide active swiper-slide-next"><a
									href="/zongyi/index_qinggan____addtime.html" data="year-0">全部</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2021__addtime.html"
									data="year-2021">2021</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2020__addtime.html"
									data="year-2020">2020</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2019__addtime.html"
									data="year-2019">2019</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2018__addtime.html"
									data="year-2018">2018</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2017__addtime.html"
									data="year-2017">2017</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2016__addtime.html"
									data="year-2016">2016</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2015__addtime.html"
									data="year-2015">2015</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2014__addtime.html"
									data="year-2014">2014</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2013__addtime.html"
									data="year-2013">2013</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2012__addtime.html"
									data="year-2012">2012</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2011__addtime.html"
									data="year-2011">2011</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2010__addtime.html"
									data="year-2010">2010</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2009__addtime.html"
									data="year-2009">2009</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2008__addtime.html"
									data="year-2008">2008</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2007__addtime.html"
									data="year-2007">2007</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2006__addtime.html"
									data="year-2006">2006</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2005__addtime.html"
									data="year-2005">2005</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2004__addtime.html"
									data="year-2004">2004</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2003__addtime.html"
									data="year-2003">2003</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2002__addtime.html"
									data="year-2002">2002</a></li>
							<li class="swiper-slide"><a href="/zongyi/index_qinggan__2001__addtime.html"
									data="year-2001">2001</a></li>

						</ul><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
					</div>
				</div>
				<div class="type-select-t">
					<ul class="conbread">
						<li><a class="text-muted">已选</a></li>
						<li class="opt"><a class="text-muted" href="/zongyi/" data="id-4">综艺<span></span></a></li>
						<li class="opt"><a href="/zongyi/index_____addtime.html" data="mcid-0">情感 <span></span></a></li>
					</ul>
				</div>
			</div>
			<!-- <div class="mcat-nav">
			<ul class="row">
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="农村">农村</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="喜剧">喜剧</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="偶像">偶像</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="古装">古装</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="武侠">武侠</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="神话">神话</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="穿越">穿越</a></li>
				<li class="col-md-4 col-sm-4 col-xs-4 pb-xs-5"><a class="text-overflow" href="#" title="刑侦">刑侦</a></li>
			</ul>
		</div> -->
		</el-card>
		<el-card style="margin-top: 20px;">
			<div>
				<span class="pull-right font13 text-muted" id="count">共有<span
						class="text-color">“{{movienumber}}”</span>部电影</span>
				<el-tabs v-model="activeName" @tab-click="handleClick">
					<el-tab-pane label="电影" name="first">
						<el-row style="overflow:hidden" class="infinite-list">
							<el-row :gutter="10" v-for="i in countrow" :key='i'>
								<el-col :span="4" v-for="ii in 6" :key='ii'>
									<el-card :body-style="{ padding: '0px' }" v-if="(((i-1)*6)+ii-1)<(movie.length)"
										class="block-collection">
										<div @click="pushToMovie(movie[((i-1)*6)+ii-1].index)">
											<img :src="movie[((i-1)*6)+ii-1].surl" class="image" />
											<span class="score">4.5</span>
											<div style="padding: 14px;" @click="pushToMovie(movie[((i-1)*6)+ii-1].index)">
												<span class="title text-overflow" :title="movie[((i-1)*6)+ii-1].title">{{movie[((i-1)*6)+ii-1].title}}</span>
												<span style="float: right;">评分：4.5</span>
												<div class="bottom clearfix">
													<time class="time">{{ movie[((i-1)*6)+ii-1].time}}</time>
												</div>
											</div>
										</div>
									</el-card>
								</el-col>
							</el-row>
						</el-row>
					</el-tab-pane>
					<el-tab-pane label="推荐电影" name="second">推荐电影</el-tab-pane>

				</el-tabs>
			</div>
			<div>

			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: "MovieLibraries",
		data() {
			return {
				movienumber: 7,
				activeName: 'first',
				count: 0,
				countrow: 0,
				movie: [],
				swiper: ['/src/assets/images/swiper/1.jpg', '/src/assets/images/swiper/2.jpg',
					'/src/assets/images/swiper/3.jpg', '/src/assets/images/swiper/4.jpg'
				],
				show:false,
			};
		},
		created() {
			this.getMovieInfor();
		},
		methods: {
			getMovieInfor() {
				for (let i = 1; i < 9; i++) {
					this.movie.push({
						index: i,
						title: "电影" + i,
						surl: "/src/assets/images/hotmovie/hot" + i + ".jpg",
						time: '2020-02-01',
					})
				}
				this.countrow = Math.ceil((this.movie.length) / 6);
			},
			pushToMovie(index) {
				this.$router.push('/movieInfor/' + index)
			},
			handleClick(tab, event) {
				console.log(tab, event);
			},
		}
	}
</script>

<style>
	.el-row {
		margin-bottom: 20px;
	
		&:last-child {
			margin-bottom: 0;
		}
	}
	
	.el-col {
		border-radius: 4px;
	}
	.card {
		height: 300px;
	
	}
	
	.time {
		font-size: 13px;
		color: #999;
	}
	.image {
		width: 100%;
		display: block;
		height: 340px;
	}
	.score {
		background-color: #fa6567;
	}
	
	.score {
		color: #fff;
		position: absolute;
		top: 40px;
		margin-left: -5px;
		padding: 2px 8px;
		text-align: right;
		font-size: 12px;
		z-index: 2;
	}
	
	.score::before {
		border-top: 4px solid #fa6567;
		border-left: 4px solid transparent;
	}
	
	.score::before {
		content: "";
		position: absolute;
		left: 1px;
		bottom: -4px;
	}
	.text-overflow {
	       width: 100px;
	       float: left;
	       overflow: hidden;
	       text-overflow: ellipsis;
	       -o-text-overflow: ellipsis;
	       white-space: nowrap;
	}
	.title:hover{
		color: #ff0000;
	}
	.layout-box {
		margin-top: 15px;
		background: #3481e6;
		padding: 10px 20px;
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		margin-left: 20px;
		margin-right: 10px;
	}
</style>
