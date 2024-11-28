<template>
  <div class="register-page">
    <el-card class="register-card" shadow="hover">
      <h2>Register</h2>
      <el-form
          :model="registerForm"
          :rules="rules"
          ref="formRef"
      label-width="100px"
      >
      <el-form-item label="UserName" prop="username">
        <el-input
            v-model="registerForm.username"
            placeholder="Please enter a username"
            @input="changeMessage($event)"
        />
      </el-form-item>
      <!-- 角色选择框 -->
      <el-form-item label="Role" prop="role">
         <el-select v-model="registerForm.role" placeholder="Please select a role">
           <el-option label="Leader" value="leader"></el-option>
           <el-option label="Member" value="member"></el-option>
         </el-select>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input
            v-model="registerForm.password"
            placeholder="Please enter your password"
            type="password"
            @input="changeMessage($event)"
        />
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input
            v-model="registerForm.email"
            placeholder="Please enter your email address"
            @input="changeMessage($event)"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onRegister">Register</el-button>
        <el-button type="text" @click="toLogin">Login</el-button>
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
      role:'',
    });

    const rules = {
      username: [{ required: true, message: 'Please enter a username', trigger: 'blur' }],
      role: [{ required: true, message: 'Please select a role', trigger: 'blur' }],
      email: [
        { required: true, message: 'Please enter your email address', trigger: 'blur' },
        { type: 'email', message: 'Please enter the correct email address', trigger: 'blur' },
      ],
      password: [{ required: true, message: 'Please enter your password', trigger: 'blur' }],
    };

    const formRef = ref(null);  // 正确的 ref 引用方式

    const onRegister = async () => {
      try {
        // 使用 formRef 进行表单验证
        // await formRef.value.validate();

        // 调用后端注册接口
        const response = await axios.post('http://localhost:8088/register', {
          name: registerForm.username,
          role: registerForm.role,
          email: registerForm.email,
          password: registerForm.password,
        });

        if (response.status === 200) {
          ElMessage.success('Successful registration, please log in!');
          router.push('/login'); // 跳转到登录页面
        } else {
          ElMessage.error('Registration failed, please try again later!');
        }
      } catch (error) {
        console.error('Registration failed:', error);
        ElMessage.error(error.response?.data?.message || 'Registration failed, please try again later!');
      }
    };

    const toLogin = () => {
      router.push('/login');
    };

    const changeMessage = () => {
      this.$forceUpdate();
    };

    return { registerForm, rules, formRef, onRegister, toLogin, changeMessage };
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
