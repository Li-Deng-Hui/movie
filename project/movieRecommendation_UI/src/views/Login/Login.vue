<template>
	<div id="login">
		<div class="box">
			<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px">
				<h3>登录</h3>
				<el-form-item label="用户" prop="username">
					<el-input v-model="ruleForm.username"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input type="password" v-model="ruleForm.password"></el-input>
				</el-form-item>

				<el-form-item>
					<el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
					<el-button @click="resetForm('ruleForm')">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
		<!-- <el-button @click="logout">退出</el-button> -->
	</div>
</template>

<script>
	export default {
		name: "Login",
		data() {
			return {
				ruleForm: {
					username: 'markerhub',
					password: '111111'
				},
				rules: {
					username: [{
							required: true,
							message: '请输入用户名',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 15,
							message: '长度在 3 到 15 个字符',
							trigger: 'blur'
						}
					],
					password: [{
						required: true,
						message: '请选择密码',
						trigger: 'change'
					}]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						const _this = this
						this.$axios.post('api/login', this.ruleForm).then(res => {

							console.log(res.data)
							const jwt = res.headers['authorization']
							const userInfo = res.data.data

							// 把数据共享出去
							_this.$store.commit("SET_TOKEN", jwt)
							_this.$store.commit("SET_USERINFO", userInfo)

							// 获取
							console.log(_this.$store.getters.getUser)


						})

					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			logout() {
				this.$axios.get('api/logout', {
						headers: {
							"Authorization": localStorage.getItem("token")
						}
					})
					.then(res => {
						console.log(res.data);
						/* 移除信息*/
						this.$store.commit("REMOVE_INFO");
					})
			}
		}
	}
</script>

<style>
	#login {
		width: 100%;
		height: 750px;
		background: url('./images/bg_admin.png');
		background-size: cover;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-flow: column;
		color: #fff;
	}

	.box {
		width: 500px;
		height: 400px;
		background-color: rgba(255, 255, 255, .8);
		display: flex;
		justify-content: center;
		align-items: center;
		flex-flow: column;
		border-radius: 4px;
		box-shadow: 0 0 8px 8px #ccc;
	}

	h3 {
		margin-bottom: 40px;
		font-size: 36px;
		letter-spacing: 2px;
		color: #888;
		text-align: center
	}
</style>
