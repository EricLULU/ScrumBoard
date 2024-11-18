<template>
  <div class="login-page">
    <el-card class="login-card" shadow="hover">
      <h2>登录</h2>
      <el-form
          :model="loginForm"
          :rules="rules"
          ref="loginForm"
          label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
              v-model="loginForm.password"
              placeholder="请输入密码"
              type="password"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onLogin">登录</el-button>
          <el-button type="text" @click="toRegister">注册</el-button>
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
      password: '',
    });

    const rules = {
      username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
    };

    const onLogin = async () => {
      try {
        // 表单验证
        const formRef = ref('loginForm');
        await formRef.value.validate();

        // 调用后端登录接口
        const response = await axios.post('http://localhost:8088/login', {
          name: loginForm.username,
          password: loginForm.password,
        });

        if (response.status === 200) {
          ElMessage.success('登录成功！');
          router.push('/app'); // 跳转到 App 页面
        } else {
          ElMessage.error('用户名或密码错误，请重试！');
        }
      } catch (error) {
        console.error('登录失败:', error);
        ElMessage.error(error.response?.data?.message || '用户名或密码错误，请重试！');
      }
    };

    const toRegister = () => {
      router.push('/register');
    };

    return { loginForm, rules, onLogin, toRegister };
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
