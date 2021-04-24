<template>
	<div id="app" class="lay-all">
		<TabBar v-if="$route.meta.showTabBar"></TabBar>
		<!-- <tab-bar v-if="$route.meta.showTabBar"/> -->
		<router-view :class="[this.$route.meta.showTabBar?'top':'']" />
	</div>
</template>

<script>
	import TabBar from './components/TabBar/TabBar'
	export default {
		components: {
			TabBar
		},
		created() {
			document.title = "----"
			//在页面加载时读取sessionStorage里的状态信息
			if (sessionStorage.getItem("store")) {
				this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(sessionStorage.getItem("store"))))
			}
			//在页面刷新时将vuex里的信息保存到sessionStorage里
			window.addEventListener("beforeunload", () => {
				sessionStorage.setItem("store", JSON.stringify(this.$store.state))
			})
		},
		data() {
			return {}
		},

	}
</script>
<style>
	@import "https://sdk-1301288745.file.myqcloud.com/s/css/theme-site.css";
	@import "https://sdk-1301288745.file.myqcloud.com/s/css/theme-cherry.css";
	/* @import url("assets/css/theme-site.css"); 
    @import url("assets/css/theme-cherry.css"); */

	@font-face {
		font-family: 'webfont';
		font-display: swap;
		src: url('//at.alicdn.com/t/webfont_3jtot46tqxq.eot');
		/* IE9*/
		src: url('//at.alicdn.com/t/webfont_3jtot46tqxq.eot?#iefix') format('embedded-opentype'),
			/* IE6-IE8 */
			url('//at.alicdn.com/t/webfont_3jtot46tqxq.woff2') format('woff2'),
			url('//at.alicdn.com/t/webfont_3jtot46tqxq.woff') format('woff'),
			/* chrome、firefox */
			url('//at.alicdn.com/t/webfont_3jtot46tqxq.ttf') format('truetype'),
			/* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
			url('//at.alicdn.com/t/webfont_3jtot46tqxq.svg#Alibaba-PuHuiTi-Regular') format('svg');
		/* iOS 4.1- */
	}

	body {
		background-color: #dde5ec;
		font-family: "webfont" !important;
		font-size: 16px;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-webkit-text-stroke-width: 0.2px;
		-moz-osx-font-smoothing: grayscale;
	}

	.top {
		margin-top: 60px !important;
	}

	.lay-all {
		width: 100%;
		height: 100%;
		position: fixed;
		top: 0;
		left: 0;
		bottom: 0;
	}

	::-webkit-scrollbar {
		width: 10px;
		height: 10px;
		overflow: auto;
	}

	::-webkit-scrollbar-thumb {
		background-color: #e6e6e6;
		min-height: 25px;
		min-width: 25px;
		border: 1px solid #e0e0e0;
		border-radius: 7px;
	}

	::-webkit-scrollbar-track {
		background-color: #f7f7f7;
		border: 1px solid #efefef;
		border-radius: 7px;
	}
</style>
