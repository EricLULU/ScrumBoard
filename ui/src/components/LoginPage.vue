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
        <!-- 用户名输入框 -->
        <el-form-item label="用户名" prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              clearable
              @input="changeMessage($event)"
          />
        </el-form-item>

        <!-- 邮箱输入框 -->
        <el-form-item label="邮箱" prop="email">
          <el-input
              v-model="loginForm.email"
              placeholder="请输入邮箱"
              clearable
              @input="changeMessage($event)"
          />
        </el-form-item>

        <!-- 密码输入框 -->
        <el-form-item label="密码" prop="password">
          <el-input
              v-model="loginForm.password"
              placeholder="请输入密码"
              type="password"
              clearable
              @input="changeMessage($event)"
          />
        </el-form-item>

        <!-- 登录与注册按钮 -->
        <el-form-item>
          <el-button type="primary" @click="onLogin">登录</el-button>
          <el-button type="text" @click="toRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { reactive, ref, nextTick } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  name: 'LoginPage',
  setup() {
    const router = useRouter();

    // 创建响应式表单数据
    const loginForm = reactive({
      username: '',
      email: '',
      password: '',
    });

    // 表单验证规则
    const rules = {
      username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' },
      ],
      password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
    };

    // 登录方法
    const onLogin = async () => {
      try {
        // 表单验证
        const formRef = ref('loginForm');
        await formRef.value.validate();

        // 调用后端登录接口
        const response = await axios.post('http://localhost:8088/login', {
          name: loginForm.username,
          email: loginForm.email,
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

    // 注册页面跳转
    const toRegister = () => {
      router.push('/register');
    };

    // 使用 nextTick 确保视图更新及时响应
    const updateInputValue = async () => {
      await nextTick();
    };

    const changeMessage =() =>{
      this.$forceUpdate()
    };

    return { loginForm, rules, onLogin, toRegister, updateInputValue, changeMessage};
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
