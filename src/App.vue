<template>
  <div id="app" class="app-container" :style="{ backgroundColor: backgroundColor }">
    <!-- Header 部分 -->
    <header class="header">
      <!-- 第一行：标题 -->
      <el-row :span="24" justify="center" align="middle">
        <el-col :span="24" class="text-center">
          <h1 class="header-title">Scrum Board</h1>
        </el-col>
      </el-row>

      <!-- 第二行：项目名称和小组数量 -->
      <el-row>
        <el-col :span="5">
          <div class="project-info text-left">
            <label for="project-name" class="bold-label">Project Name: </label>
            <input id="project-name" v-model="projectName" class="header-input"/>
          </div>
        </el-col>
        <el-col :span="14">
          <div class="project-info text-right">
            <label for="number-of-group" class="bold-label">Number of Members: </label>
            <input
                id="number-of-group"
                v-model.number="numberOfGroup"
                type="number"
                class="header-input"
                min="1"
            />
          </div>
        </el-col>
      </el-row>
    </header>

    <!-- Body 部分 -->
    <div class="body-container" ref="bodyContainer">
      <el-row :gutter="20">
        <el-col v-for="(column, index) in columns" :key="index" :span="4" class="column-content">
          <div class="column-header">
            <input v-model="columns[index]" class="column-input"/>
          </div>
        </el-col>
      </el-row>

      <div class="note-container">
        <StickyNote
            v-for="note in notes"
            :key="note.id"
            :id="note.id"
            :text="note.text"
            :color="note.color"
            :position="note.position"
            @remove="removeNote"
        />
      </div>
    </div>

    <!-- Footer 部分 -->
    <footer class="footer">
      <button @click="addNote" class="add-note-button">
        <el-icon color="#06f526" size="30px">
          <Tickets/>
        </el-icon>
      </button>
    </footer>

  </div>
</template>

<script>
import StickyNote from "@/components/StickyNote.vue";
import {Tickets} from '@element-plus/icons-vue';

export default {
  components: {
    StickyNote,
    Tickets,
  },
  data() {
    return {
      projectName: 'Scrum Project',
      numberOfGroup: 1,
      notes: [],
      nextId: 1,
      columns: ['backlog', 'To-Do', 'In-Process', 'Done', 'Problem', 'Solution'],
      backgroundColor: '#2b2b2b', // 黑板风格背景颜色
    };
  },
  mounted() {
    this.bodyContainer = this.$refs.bodyContainer;
  },
  methods: {
    addNote() {
      if (!this.bodyContainer) {
        console.error('Body container is not loaded');
        return;
      }
      const bodyContainerRect = this.bodyContainer.getBoundingClientRect();
      const newNote = {
        id: this.nextId++,
        text: 'Double click to edit',
        color: '#FFD700',
        position: {
          top: bodyContainerRect.height - 200,
          left: (bodyContainerRect.width / 2) - 110,
        },
      };
      this.notes.push(newNote);
    },
    removeNote(id) {
      this.notes = this.notes.filter((note) => note.id !== id);
    },
  },
};
</script>

<style>
/* 全局布局样式 */
body {
  margin: 0;
  padding: 0;
  font-family: 'Chalkduster', sans-serif; /* 使用手写风格字体 */
  background-color: #2b2b2b; /* 黑板背景色 */
}

.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: #2b2b2b; /* 黑板颜色 */
}

/* Header 样式 */
.header {
  background-color: #333333; /* 深灰色背景 */
  padding: 20px;
}

.header-title {
  font-size: 48px;
  font-weight: bold;
  color: #ffffff; /* 白色字体模拟粉笔效果 */
  text-align: center;
}

.bold-label, .bold-input {
  font-weight: bold;
  font-size: 20px;
  color: #ffffff; /* 白色字体 */
}

.header-input {
  font-weight: bold;
  color: #111111; /* 黑色字体 */
}

.project-info {
  margin: 10px 0;
}

/* Body 样式 */
.body-container {
  flex-grow: 1;
  padding: 20px;
  text-align: center;
  align-items: center;
}

.column-content {
  text-align: center;
  padding: 20px;
  border: 1px dashed #ffffff; /* 模拟粉笔写的表格 */
}

.column-header {
  font-size: 40px;
  font-weight: bold;
  color: #ffffff; /* 白色字体 */
}

.column-input {
  width: 100%;
  font-size: 40px;
  text-align: center;
  font-weight: bold;
  color: #ffffff; /* 白色字体 */
  background-color: transparent;
  border: none;
  outline: none;
}

/* Note 容器样式 */
.note-container {
  position: relative;
  width: 100%;
  text-align: left;
}

/* Footer 样式 */
.footer {
  background-color: #333333;
  padding: 20px;
  text-align: center;
}

.add-note-button {
  background-color: #ffffff;
  color: #333333;
  border: 2px solid #06f526;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 20px;
  border-radius: 5px;
}
</style>
