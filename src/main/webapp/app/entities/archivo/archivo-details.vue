<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="archivo">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.archivo.detail.title')">Archivo</span> {{archivo.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.archivo.nombre')">Nombre</span>
                    </dt>
                    <dd>
                        <span>{{archivo.nombre}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.archivo.dato')">Dato</span>
                    </dt>
                    <dd>
                        <div v-if="archivo.dato">
                            <a v-on:click="openFile(archivo.datoContentType, archivo.dato)" v-text="$t('entity.action.open')">open</a>
                            {{archivo.datoContentType}}, {{byteSize(archivo.dato)}}
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="archivo.id" :to="{name: 'ArchivoEdit', params: {archivoId: archivo.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./archivo-details.component.ts">
</script>
