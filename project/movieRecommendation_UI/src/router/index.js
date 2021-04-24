import Vue from "vue";
import VueRouter from "vue-router";
import shouye from "../views/shouye.vue"
import Login from "../views/Login/Login.vue"
import Home from "../views/admin/Home.vue"
import AdminLogin from '../views/admin/Login/Login'
import UserManage from '../views/admin/children/UserManage.vue'
import MovieManage from '../views/admin/children/MovieManage'
import MovieSchedule from '../views/admin/children/MovieSchedule'
import CommentManage from '../views/admin/children/CommentManage'

import PersonalCenter from '../views/personal_center.vue'
import MovieInfor from '../views/movie_infor.vue'
import MovieLibraries from '../views/movie_libraries.vue'
import RankingList from '../views/ranking_list.vue'
import BeiJing from '../views/bj.vue'

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err);
};

Vue.use(VueRouter);

const routes = [{
		path: "/",
		name: "ShouYe",
		component: shouye,
		meta: {
			showTabBar: true,
		},
	},
	{
		path:'/beijing',
		name:"beijing",
		component:BeiJing,
	},
	{
		path: '/pseronalCenter/:sort',
		component: PersonalCenter,
		name: "PersonalCenter",
		meta: {
			showTabBar: true,
		},
	},
	{
		path:'/movieInfor/:movieId',
		name:"MovieInfor",
		component:MovieInfor,
		
	},
	{
		path:'/movieLibraries',
		component:MovieLibraries,
		name:"MovieLibraries",
		meta:{
			showTabBar:true,
		}
	},
	{
		path:'/rankingList',
		name:"RankingList",
		component:RankingList,
		meta:{
			showTabBar:true,
		}
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
		meta: {
			showTabBar: false,
		}
	},

	{
		path: '/adminLogin',
		name: 'AdminLogin',
		component: AdminLogin,
		meta: {
			showTabBar: false,
		}
	},
	{
		path: '/admin/home',
		name: 'Home',
		component: Home,
		meta: {
			requireAuth: true,
			showTabBar: false,
		},
		children: [{
				path: 'user_manage',
				component: UserManage
			},
			{
				path: 'movie_manage',
				component: MovieManage
			},
			{
				path: 'movie_schedule',
				component: MovieSchedule
			},
			{
				path: 'comment_manage',
				component: CommentManage
			},
			{
				path: '/admin/home',
				redirect: '/admin/home/user_manage'
			},
			{
				path: 'pseronalCenter/:sort',
				component: PersonalCenter,
				name: "adminPersonalCenter"
			},
		]
	},
];

const router = new VueRouter({
	routes
});

export default router;
