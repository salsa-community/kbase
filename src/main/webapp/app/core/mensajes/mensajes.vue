<template>
  <div class="form-group">
    <div class="container">
      <draggable
        class="container"
        v-if="mensajes"
        v-model="mensajes"
        @change="orderSteps"
        @start="drag = true"
        @end="drag = false"
        v-bind="dragOptions"
      >
        <transition-group type="transition" name="flip-list">
          <div class="row pb-1" v-for="step in mensajes" :key="step.orden">
            <div class="draggable col mr-1 step-row text-center"></div>
            <div class="mensaje col-sm-9 step-row">
              <b-form-textarea name="step" rows="2" no-resize v-model="step.desc"> </b-form-textarea>
            </div>
            <div class="col-sm-2">
              <div class="btn-group">
                <el-popconfirm
                  v-on:confirm="remove(step)"
                  confirmButtonText="Aceptar"
                  cancelButtonText="Cancelar"
                  icon="el-icon-warning"
                  iconColor="red"
                  title="¿Estás seguro de eliminar este mensaje?"
                >
                  <el-button slot="reference" type="danger" icon="el-icon-delete" circle></el-button>
                </el-popconfirm>
              </div>
            </div>
          </div>
        </transition-group>
      </draggable>
      <div>
        <b-button block v-on:click="add()" class="btn btn-sm" variant="outline-info">Agregar Mensaje </b-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./mensajes.component.ts">
</script>


<style scoped>
.mensaje:before {
  font-family: FontAwesome;
  content: '\f00a';
  position: absolute;
  top: 11px;
  left: -30px;
  transition: all 300ms ease;
}

.draggable:hover ~ .mensaje {
}
</style>
