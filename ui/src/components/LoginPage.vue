<template>
  <div class="login-page">
    <el-card class="login-card" shadow="hover">
      <h2>Login</h2>
      <el-form
          :model="loginForm"
          :rules="rules"
          ref="formRef"
          label-width="100px"
      >
        <el-form-item label="User Name" prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="Please enter a username."
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input
              v-model="loginForm.email"
              placeholder="Please enter your email address."
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input
              v-model="loginForm.password"
              placeholder="Please enter your password."
              type="password"
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onLogin">Login</el-button>
          <el-button type="text" @click="toRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  name: 'LoginPage',
  setup() {
    const router = useRouter();
    const loginForm = reactive({
      username: '',
      email: '',
      password: '',
    });

    const rules = {
      username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' },
      ],
      password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
    };

    const formRef = ref(null);  // 正确的 ref 引用方式

    const onLogin = async () => {
      try {
        // 使用 formRef 进行表单验证
        // await formRef.value.validate();

        // 调用后端注册接口
        const response = await axios.post('http://localhost:8088/login', {
          name: loginForm.username,
          email: loginForm.email,
          password: loginForm.password,
        });

        if (response.status === 200) {
          ElMessage.success('登入成功！');
          router.push('/'); // 跳转到app页面
        } else {
          ElMessage.error('用户名或密码错误，请重试！');
        }
      } catch (error) {
        console.error('注册失败:', error);
        ElMessage.error(error.response?.data?.message || '用户名或密码错误，请重试！');
      }
    };

    // 注册页面跳转
    const toRegister = () => {
      router.push('/register');
    };

    const changeMessage = () => {
      this.$forceUpdate();
    };

    return { loginForm, rules, formRef, onLogin, toRegister, changeMessage };
  },
};
</script>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}
.login-card {
  width: 400px;
  padding: 20px;
}
</style>
