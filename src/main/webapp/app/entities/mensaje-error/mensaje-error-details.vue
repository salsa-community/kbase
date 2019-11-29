<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="mensajeError">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.mensajeError.detail.title')">MensajeError</span> {{mensajeError.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.mensajeError.clave')">Clave</span>
                    </dt>
                    <dd>
                        <span>{{mensajeError.clave}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.mensajeError.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{mensajeError.desc}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.mensajeError.instruccion')">Instruccion</span>
                    </dt>
                    <dd>
                        <div v-if="mensajeError.instruccionId">
                            <router-link :to="{name: 'InstruccionView', params: {instruccionId: mensajeError.instruccionId}}">{{mensajeError.instruccionId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="mensajeError.id" :to="{name: 'MensajeErrorEdit', params: {mensajeErrorId: mensajeError.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./mensaje-error-details.component.ts">
</script>
