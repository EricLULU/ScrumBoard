<template>
  <div class="register-page">
    <el-card class="register-card" shadow="hover">
      <h2>注册</h2>
      <el-form
          :model="registerForm"
          :rules="rules"
          ref="registerForm"
          label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
              v-model="registerForm.username"
              placeholder="请输入用户名"
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input
              v-model="registerForm.email"
              placeholder="请输入邮箱"
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
              v-model="registerForm.password"
              placeholder="请输入密码"
              type="password"
              @input="changeMessage($event)"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onRegister">注册</el-button>
          <el-button type="text" @click="toLogin">返回登录</el-button>
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
  name: 'RegisterPage',
  setup() {
    const router = useRouter();
    const registerForm = reactive({
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

    const onRegister = async () => {
      try {
        // 表单验证
        const formRef = ref('registerForm');
        await formRef.value.validate();

        // 调用后端注册接口
        const response = await axios.post('http://localhost:8088/register', {
          name: registerForm.username,
          email: registerForm.email,
          password: registerForm.password,
        });

        if (response.status === 200) {
          ElMessage.success('注册成功，请登录！');
          router.push('/login'); // 跳转到登录页面
        } else {
          ElMessage.error('注册失败，请稍后再试！');
        }
      } catch (error) {
        console.error('注册失败:', error);
        ElMessage.error(error.response?.data?.message || '注册失败，请稍后再试！');
      }
    };

    const toLogin = () => {
      router.push('/login');
    };

    const changeMessage =() =>{
      this.$forceUpdate()
    };

    return { registerForm, rules, onRegister, toLogin, changeMessage};
  },
};
</script>

<style scoped>
.register-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}
.register-card {
  width: 400px;
  padding: 20px;
}
</style>
