<template>
  <div
      class="sticky-note"
      :style="{ top: `${notePosition.top}px`, left: `${notePosition.left}px`, backgroundColor: noteColor }"
      @mousedown="startDrag"
      @mouseenter="showIcons = true"
      @mouseleave="showIcons = false"
  >
    <textarea
        v-if="isEditing"
        v-model="noteText"
        @blur="isEditing = false"
        class="note-text"
    ></textarea>
    <p v-else @dblclick="isEditing = true">{{ noteText }}</p>

    <!-- 图标容器，只有在显示图标的状态下渲染 -->
    <div class="icon-container" v-if="showIcons">
      <!-- 画笔图标用于颜色选择 -->
      <el-icon @click="showColorPicker = true" class="icon-button">
        <Brush />
      </el-icon>

      <el-icon @click="removeNote" class="delete-icon">
        <Delete />
      </el-icon>
    </div>

    <!-- 颜色选择器 -->
    <el-color-picker
        v-model="noteColor"
        :show-alpha="true"
        v-if="showColorPicker"
        @change="hideColorPicker"
    ></el-color-picker>
  </div>
</template>

<script>
import { Brush, Delete } from '@element-plus/icons-vue';

export default {
  props: {
    id: Number,
    text: String,
    color: String,
    position: Object,
  },
  components: {
    Brush,
    Delete,
  },
  data() {
    return {
      noteText: this.text || '双击编辑便签',
      noteColor: this.color || '#FFD700',
      notePosition: this.position || { top: 100, left: 100 },
      isEditing: false,
      showColorPicker: false,
      dragging: false,
      dragStartX: 0,
      dragStartY: 0,
      showIcons: false, // 控制图标显示的布尔值
    };
  },
  methods: {
    startDrag(event) {
      this.dragging = true;
      this.dragStartX = event.clientX - this.notePosition.left;
      this.dragStartY = event.clientY - this.notePosition.top;
      document.addEventListener('mousemove', this.dragNote);
      document.addEventListener('mouseup', this.stopDrag);
    },
    dragNote(event) {
      if (!this.dragging) return;
      this.notePosition.left = event.clientX - this.dragStartX;
      this.notePosition.top = event.clientY - this.dragStartY;
    },
    stopDrag() {
      this.dragging = false;
      document.removeEventListener('mousemove', this.dragNote);
      document.removeEventListener('mouseup', this.stopDrag);
    },
    removeNote() {
      this.$emit('remove', this.id); // 触发父组件的删除事件，传递便签的 ID
    },
    hideColorPicker() {
      this.showColorPicker = false;
    },
  },
};
</script>

<style scoped>
.sticky-note {
  position: absolute;
  width: 220px;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  background-color: #fffae3;
}

.note-text {
  width: 100%;
  height: 100%;
  border: none;
  background: transparent;
  resize: none;
  font-family: 'Arial', sans-serif;
  font-size: 14px;
  color: #333;
}

.icon-container {
  display: flex;
  flex-direction: row;
  justify-content: left;
  margin-left: 10px;
}

.icon-button,
.delete-icon {
  cursor: pointer;
  margin: 5px 0;
  color: #333;
}
</style>
