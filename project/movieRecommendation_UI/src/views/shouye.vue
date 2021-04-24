<template>
	<el-container>
		<el-header style="height: 40%;">
			<el-carousel :interval="4000" type="card">
				<el-carousel-item v-for="(surl,key) in swiper" :key="key">
					<img :src="surl" />
					<span class="e-carousel-span">电影名</span>
				</el-carousel-item>
			</el-carousel>
		</el-header>
		<el-main>
			<el-row :gutter="20">
				<!-- <el-col :span="6">
					<div class="grid-content bg-purple block-collection">
						<h1>首页</h1>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="grid-content bg-purple block-collection">
						<h1>影库</h1>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="grid-content bg-purple block-collection">
						<h1>电影快报</h1>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="grid-content bg-purple block-collection">
						<h1>个人中心</h1>
					</div>
				</el-col> -->

				<div class="layout-box">
					<div class="box-title"><span class="iconfont icon-remen"></span>
						<h2>最新推荐</h2>
					</div>
				</div>
			</el-row>
			<el-row style="overflow:hidden" class="infinite-list" v-loading="loading" element-loading-text="拼命加载中"
				element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
				<el-row :gutter="10" v-for="i in countrow" :key='i'>
					<el-col :span="4" v-for="ii in 6" :key='ii'>
						<el-card :body-style="{ padding: '0px' }" v-if="(((i-1)*6)+ii-1)<(movie.length)"
							class="block-collection">
							<el-tooltip :content="movie[((i-1)*6)+ii-1].title" placement="bottom" effect="light">
							<div @click="pushToMovie(movie[((i-1)*6)+ii-1].id)">
								<img :src="movie[((i-1)*6)+ii-1].cover" class="image" referrerPolicy="no-referrer" />
								<span class="score">{{movie[((i-1)*6)+ii-1].rate}}</span>
								<div style="padding: 14px;" @click="pushToMovie(movie[((i-1)*6)+ii-1].id)">
									<span class="title text-overflow"
										:title="movie[((i-1)*6)+ii-1].title">电影名：{{movie[((i-1)*6)+ii-1].title}}</span>
									<span style="float: right;">评分：{{movie[((i-1)*6)+ii-1].rate}}</span>
									
									<!-- <div class="bottom clearfix">
										<time class="time">{{ movie[((i-1)*6)+ii-1].time}}</time>
									</div> -->
								</div>
							</div>
							</el-tooltip>
						</el-card>
					</el-col>
				</el-row>
			</el-row>
			<el-row>
				<el-col>
					<el-button style="width: 100%;" @click="getMovieInfor(++index)">加载更多......</el-button>
				</el-col>
			</el-row>



			<el-backtop :bottom="150" :visibility-height="10">
				<div style="{
			        height: 100%;
			        width: 100%;
					border-radius: 6px;
			        box-shadow: 0 0 6px rgba(0,0,0, .12);
			        text-align: center;
			        line-height: 40px;
			        color: #1989fa;
			      }">
					<i class="el-icon-upload2"></i>
				</div>
			</el-backtop>
			<el-backtop :bottom="100" :visibility-height="10">
				<div style="{
			          height: 100%;
			          width: 100%;
			  		border-radius: 6px;
			          box-shadow: 0 0 6px rgba(0,0,0, .12);
			          text-align: center;
			          line-height: 40px;
			          color: #1989fa;
			        }">
					<i class=" el-icon-s-promotion"></i>
				</div>
			</el-backtop>
		</el-main>
		<el-footer>

		</el-footer>
	</el-container>
</template>
<script>
	export default {
		name: "Shouye",
		data() {
			return {
				count: 0,
				countrow: 0,
				movie: [],
				swiper: ['/src/assets/images/swiper/1.jpg', '/src/assets/images/swiper/2.jpg',
					'/src/assets/images/swiper/3.jpg', '/src/assets/images/swiper/4.jpg'
				],
				loading: false,
				index: 0,
			};
		},
		created() {
			this.getMovieInfor(this.index);
			var paramsData = new URLSearchParams();
			paramsData.append("wd", "灵域")
			/* this.$axios.post('/sm/search/',paramsData)
			.then((response)=>{
				let data=response.data;
				console.log(data);
				var obj = window.open("about:blank");  
				obj.document.write(data);  
			}); */
		},
		methods: {
			getMovieInfor(index) {
				this.loading = true;
				this.$axios.get('douban/j/search_subjects', {
						params: {
							type: 'movie',
							tag: '热门',
							page_limit: 30,
							page_start: index
						}
					})
					.then((response) => {
						let data = response.data;
						this.movie.push.apply(this.movie, data.subjects);
						console.log(this.movie);
						this.countrow = Math.ceil((this.movie.length) / 6);
						this.loading = false;
					});
				/* for (let i = 1; i < 9; i++) {
					this.movie.push({
						index: i,
						title: "电影fsfsfsssssssssssssssssssssssssssssssssssssssss" + i,
						surl: "/src/assets/images/hotmovie/hot" + i + ".jpg",
						time: '2020-02-01',
					})
					this.countrow = Math.ceil((this.movie.length) / 6);
				} */

			},
			pushToMovie(index) {
				this.$router.push('/movieInfor/' + index)
			},
		}
	}
</script>
<style>
	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 200px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n+1) {
		background-color: #d3dce6;
	}

	.el-row {
		margin-bottom: 20px;

		&:last-child {
			margin-bottom: 0;
		}
	}

	.el-col {
		border-radius: 4px;
	}

	.bg-purple {
		background: #d3dce6;
	}

	.grid-content {
		border-radius: 4px;
		min-height: 36px;
		text-align: center;
	}

	.card {
		height: 300px;

	}

	.time {
		font-size: 13px;
		color: #999;
	}

	.bottom {
		margin-top: 13px;
		line-height: 12px;
	}

	.button {
		padding: 0;
		float: right;
	}

	.image {
		width: 100%;
		display: block;
		height: 340px;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}

	.block-collection:hover {
		transform: translateY(-2%);
		box-shadow: 1px 4px 10px 2px #ccc;
	}

	.block-collection {
		margin: 20px 3px 2px 12px;
		cursor: pointer;
	}

	.el-backtop {
		position: fixed;
		background-color: #FFF;
		width: 40px;
		height: auto;
		border-radius: 4px;
		display: -ms-flexbox;
		display: flex;
		-ms-flex-align: center;
		align-items: center;
		-ms-flex-pack: center;
		justify-content: center;
		font-size: 20px;
		box-shadow: 0 0 6px rgb(0 0 0 / 12%);
		cursor: pointer;
		z-index: 5;
	}

	.layout-box {
		margin-top: 15px;
		background: #3481e6;
		padding: 10px 20px;
		box-shadow: 0 1px 4px rgba(0, 0, 0, .07);
		margin-left: 20px;
		margin-right: 10px;
	}

	.box-title h2,
	.box-title h1 {
		display: inline-block;
		font-size: 18px !important;
		margin-bottom: 0px !important;
		color: #fff;
	}

	.box-title .iconfont {
		color: #fa6567;
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

	.title:hover {
		color: #ff0000;
	}

	.e-carousel-span {
		z-index: 2;
		width: 100%;
		position: absolute;
		display: inline-block;
		line-height: 20px;
		color: #fff;
		background: rgba(0, 0, 0, .6);
		padding: 3px 9px;
		font-size: 12px;
		bottom: 0px;
		
	}
</style>
