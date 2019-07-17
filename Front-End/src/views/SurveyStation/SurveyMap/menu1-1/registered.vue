<template>
		<div class="layout">
		<Layout>
			<Header>
				<Menu mode="horizontal" theme="dark">
					<div class="layout-logo">
						<img src="../../../../../../../作业/java应用短学期/31602037_陈俊杉_JavaScript_大作业/lol_view/src/assets/logo.png" />
					</div>
					<div class="layout-nav">
						<MenuItem name="1">
						<Icon type="ios-navigate"></Icon>
						问题反馈
						</MenuItem>
						<MenuItem name="2">
						<Icon type="ios-keypad"></Icon>
						手机App
						</MenuItem>
						<MenuItem name="3">
						<Icon type="ios-analytics"></Icon>
						登陆
						</MenuItem>
					</div>
				</Menu>
			</Header>
			<div style="height: 20px;"></div>
			<Card>
				<h1>注册</h1>
			</Card>
			<div style="height: 10px;"></div>
			<Card>
				<div style="display: flex;justify-content: center;align-items: center;">
				<div style="min-height: 370px; text-align: center; width: 400px;">
					<Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
						<FormItem label="Name" prop="name">
							<Input v-model="formValidate.name" placeholder="请输入名称"></Input>
						</FormItem>
						<FormItem label="Password" prop="passwd">
							<Input type="password" v-model="formValidate.passwd" placeholder="请输入密码"></Input>
						</FormItem>
						<FormItem label="Confirm" prop="passwdCheck">
							<Input type="password" v-model="formValidate.passwdCheck" placeholder="请再次输入密码"></Input>
						</FormItem>
						<FormItem label="E-mail" prop="mail">
							<Input v-model="formValidate.mail" placeholder="请输入邮箱"></Input>
						</FormItem>
						<FormItem label="Phone" prop="phone">
							<Input v-model="formValidate.phone" placeholder="请输入你的手机号"></Input>
						</FormItem>
						<FormItem label="Title" prop="usrc">
							<div>
								<input type="file" name="file" accept=".jpg, .jpeg, .png" @change="uploadAvatar">
							</div>
						</FormItem>
						<FormItem>
							<Button @click="handleReset('formValidate')" style="margin-left: -100px" >清空</Button>
							<Button type="primary" @click="handleSubmit('formValidate')" style="margin-left: 20px">注册</Button>
						</FormItem>
					</Form>
				</div>
				</div>
			</Card>
			</Content>
			<Footer class="layout-footer-center">2019-2020 &copy; 陈俊杉-英雄联盟</Footer>
		</Layout>
	</div>

</template>
<script>
export default
{
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter your password'))
      } else {
        if (this.formValidate.passwdCheck !== '') {
          // 对第二个密码框单独验证
          this.$refs.formValidate.validateField('passwdCheck')
        }
        callback()
      }
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter your password again'))
      } else if (value !== this.formValidate.passwd) {
        callback(new Error('The two input passwords do not match!'))
      } else {
        callback()
      }
    }
    return {
      uploadList: [],
      visible: false,
      tupiansrc: 'http://localhost:3000/uploads/',
      formValidate: {
        name: '',
        mail: '',
        passwd: '',
        passwdCheck: '',
        phone: '',
        userPower: 0,
        userAdmin: false,
        userStop: false,
        usrc: '',
        productlogo: ''
      },
      ruleValidate: {
        name: [{
          required: true,
          message: 'The name cannot be empty',
          trigger: 'blur'
        }],
        mail: [{
          required: true,
          message: 'Mailbox cannot be empty',
          trigger: 'blur'
        },
        {
          type: 'email',
          message: 'Incorrect email format',
          trigger: 'blur'
        }
        ],
        phone: {
          required: true,
          message: 'Please select the phone',
          trigger: 'blur'
        },
        passwd: [{
          required: true,
          validator: validatePass,
          message: '密码为空！',
          trigger: 'blur'
        }],
        passwdCheck: [{
          required: true,
          validator: validatePassCheck,
          message: '需要您再次输入密码',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    uploadAvatar(avatar) {
      console.log(avatar.target.files[0])
      const file = avatar.target.files[0]
      const data = new FormData()
      data.append('file', file, file.name)// 很重要 data.append("file", file);不成功
      data.append('data', 112)
      console.log(data.get('file'))
      return this.axios.post('http://localhost:3000/users/file', data, {
        headers: { 'content-type': 'multipart/AvgStatistics-data' }
      }).then((data) => {
		  console.log(data)
		  this.usersrc = data.data.filename
	  })
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.axios.post('http://localhost:3000/users/register', {
            username: this.formValidate.name,
            password: this.formValidate.passwd,
            userMail: this.formValidate.mail,
            userPhone: this.formValidate.phone,
            userPower: 0,
            userAdmin: false,
            userStop: false,
            usersrc: this.tupiansrc + this.usersrc
          }).then((data) => {
            if (data.data.status == 1) {
              this.$Message.error(data.data.message)
            } else {
              this.$Message.success(data.data.message)
              this.$router.go(-1)
            }
          })
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    handleReset(name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>
<style>
	.layout {
		border: 1px solid #d7dde4;
		background: #f5f7f9;
		position: relative;
		border-radius: 4px;
		overflow: hidden;
	}

	.layout-logo {
		width: 100px;
		height: 30px;
		border-radius: 3px;
		float: left;
		position: relative;
		top: 5px;
		left: -40px;
	}

	.layout-nav {
		width: 420px;
		margin: 0 auto;
		margin-right: -120px;
	}

	.layout-footer-center {
		text-align: center;
	}
</style>
