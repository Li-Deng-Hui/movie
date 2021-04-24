<template>
	<div id="tab-bar">
		<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#3481e6"
			text-color="#fff" active-text-color="#ffd04b" :router="true" @select="handleSelect">
			<el-menu-item index="1" route="/">首页</el-menu-item>
			<el-menu-item index="2" route="/movieLibraries">电影库</el-menu-item>
			<!-- <el-submenu index="2">
				<template slot="title">电影库</template>
				<el-menu-item index="2-1" route="/movieLibraries">全部</el-menu-item>
				<el-menu-item index="2-2">选项2</el-menu-item>
				<el-menu-item index="2-3">选项3</el-menu-item>
			</el-submenu> -->
			<el-menu-item index="3" route="/rankingList">热播排行</el-menu-item>
			<el-menu-item index="4" route="/pseronalCenter/qb">个人信息</el-menu-item>
			<div class="e-touxian">
				<el-dropdown>
					<span class="el-dropdown-link">
						<el-badge :value="12" class="item" :max="99">
							<el-avatar icon="el-icon-user-solid" shape="square"></el-avatar>
						</el-badge>
					</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item icon="el-icon-user">个人信息</el-dropdown-item>
						<el-dropdown-item icon="el-icon-star-on">收藏
							<el-badge class="mark" :value="12" :max="99" />
						</el-dropdown-item>
						<el-dropdown-item icon="el-icon-s-comment">评论
							<el-badge class="mark" :value="12" :max="99" />
						</el-dropdown-item>
						<el-dropdown-item icon="el-icon-remove"><span @click="logout">注销</span></el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>
			<div class="e-search">
				<!-- <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
					<el-button slot="append" icon="el-icon-search"></el-button>
				</el-input> -->
				<el-autocomplete class="input-with-select" popper-class="my-autocomplete" v-model="input"
					:fetch-suggestions="querySearchAsync" placeholder="请输入内容" @select="searchHandleSelect">
					<el-button slot="append" icon="el-icon-search" @click="query"></el-button>
					<template slot-scope="{ item }">
						<a :href="item.url" target="_blank">
							<img :src="item.img" width="40" referrerPolicy="no-referrer">
							<p> <em>{{item.title}}</em> <span>{{item.year}}</span> <br>
							<span>{{item.sub_title}}</span>
							</p>
						</a>
					</template>
				</el-autocomplete>
			</div>
		</el-menu>

	</div>
</template>

<script>
	export default {
		name: "TabBar",
		data() {
			return {
				activeIndex: this.$store.state.activeIndex.toString(),
				input: '',
				restaurants: [],
				state: '',
				timeout: null
			};
		},
		methods: {
			query() {
				window.open("https://www.ism188.com/search/?wd=" + this.input);
			},
			querySearchAsync(queryString, cb) {
				var restaurants = this.restaurants;
				var results = [];
				this.$axios.get('douban/j/subject_suggest?q='+queryString)
					.then((response) => {
						console.log(response.data)
						results = response.data;
						cb(results);
					})
			},

			handleSelect(item) {
				console.log(item);
				this.activeIndex = item;
				this.$store.commit("SET_ACTIVEINDEX", this.activeIndex);
			},
			searchHandleSelect(item) {
				console.log(item);
			},
			logout() {
				const _this = this
				_this.$axios.get("/logout", {
					headers: {
						"Authorization": localStorage.getItem("token")
					}
				}).then(res => {
					_this.$store.commit("REMOVE_INFO")
					_this.$router.push("/login")

				})
			}
		},
		mounted() {

		}
	}
</script>

<style>
	#tab-bar {
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
	}

	.el-dropdown-link {
		cursor: pointer;
		color: #409EFF;
	}

	.el-icon-arrow-down {
		font-size: 12px;
	}

	.e-touxian {
		float: right;
		margin-right: 20px;
		/* margin-top: 10px; */
	}

	.e-search {
		float: right;
		margin-right: 20px;
		margin-top: 10px;
	}

	.input-with-select .el-input-group__prepend {
		background-color: #fff;
	}

	.item {
		margin-top: 10px;
		margin-right: 40px;
	}

	.my-autocomplete li {
		line-height: normal;
		padding: 7px;
	}

	.my-autocomplete li img {
		float: left;
		    margin-right: 8px;
		    margin-top: 3px;
	}

	.my-autocomplete li span {
		font-size: 12px;
		color: #b4b4b4;
	}

	.my-autocomplete li .highlighted span {
		color: #ddd;
	}
</style>
