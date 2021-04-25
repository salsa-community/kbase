<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.contexto.home.createOrEditLabel" v-text="$t('kbaseApp.contexto.home.createOrEditLabel')">Create or edit a Contexto</h2>
                <b-tabs content-class="mt-4" fill pills small>
                    <b-tab title="Datos generales">
                        <template #title>
                            <b-spinner type="grow" small></b-spinner> I'm <i>custom</i> <strong>title</strong>
                        </template>
                        <div class="form-group" v-if="contexto.id">
                            <label for="id" v-text="$t('global.field.id')">ID</label>
                            <input type="text" class="form-control" id="id" name="id"
                                v-model="contexto.id" readonly />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.nombre')" for="contexto-nombre">Nombre</label>
                            <input type="text" class="form-control" name="nombre" id="contexto-nombre"
                                :class="{'valid': !$v.contexto.nombre.$invalid, 'invalid': $v.contexto.nombre.$invalid }" v-model="$v.contexto.nombre.$model" />
                        </div>

                        <b-form-group
                            description="Con esta clave se puede registrar un bot en un sitio web"
                            :label="$t('kbaseApp.contexto.clave')"
                            label-for="contexto-clave">

                            <input type="text" class="form-control" name="clave" id="contexto-clave"
                                :class="{'valid': !$v.contexto.clave.$invalid, 'invalid': $v.contexto.clave.$invalid }"
                                v-model="$v.contexto.clave.$model" />

                        </b-form-group>
                        
                        <div class="form-group">

                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.desc')" for="contexto-desc">Desc</label>
                            <input type="text" class="form-control" name="desc" id="contexto-desc"
                                :class="{'valid': !$v.contexto.desc.$invalid, 'invalid': $v.contexto.desc.$invalid }" v-model="$v.contexto.desc.$model" />
                        
                        </div>

                        <b-form-group
                            :label="$t('kbaseApp.contexto.descEn')"
                            label-for="contexto-descEn">
                            
                            <input type="text" class="form-control" name="descEn" id="contexto-descEn"
                                :class="{'valid': !$v.contexto.descEn.$invalid, 'invalid': $v.contexto.descEn.$invalid }" v-model="$v.contexto.descEn.$model" />

                        </b-form-group>
                    </b-tab>
                    <b-tab title="Datos de la organización">
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.organizacion')" for="contexto-organizacion">organizacion</label>
                            <input type="text" class="form-control" name="desc" id="contexto-organizacion"
                                :class="{'valid': !$v.contexto.organizacion.$invalid, 'invalid': $v.contexto.organizacion.$invalid }" v-model="$v.contexto.organizacion.$model" />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivo')" for="contexto-objetivo">objetivo</label>
                            <input type="text" class="form-control" name="objetivo" id="contexto-objetivo"
                                :class="{'valid': !$v.contexto.objetivo.$invalid, 'invalid': $v.contexto.objetivo.$invalid }" v-model="$v.contexto.objetivo.$model" />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivoEn')" for="contexto-objetivoEn">objetivoEn</label>
                            <input type="text" class="form-control" name="objetivoEn" id="contexto-objetivoEn"
                                :class="{'valid': !$v.contexto.objetivoEn.$invalid, 'invalid': $v.contexto.objetivoEn.$invalid }" v-model="$v.contexto.objetivoEn.$model" />
                        </div>
                    </b-tab>
                    <b-tab title="Mensajes del chatbot">
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.loginMessage')" for="contexto-loginMessage">loginMessage</label>
                            <input type="text" class="form-control" name="loginMessage" id="contexto-loginMessage"
                                :class="{'valid': !$v.contexto.loginMessage.$invalid, 'invalid': $v.contexto.loginMessage.$invalid }" v-model="$v.contexto.loginMessage.$model" />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.loginMessageEn')" for="contexto-loginMessageEn">loginMessageEn</label>
                            <input type="text" class="form-control" name="loginMessageEn" id="contexto-loginMessageEn"
                                :class="{'valid': !$v.contexto.loginMessageEn.$invalid, 'invalid': $v.contexto.loginMessageEn.$invalid }" v-model="$v.contexto.loginMessageEn.$model" />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessage')" for="contexto-welcomeMessage">welcomeMessage</label>
                            <input type="text" class="form-control" name="welcomeMessage" id="contexto-welcomeMessage"
                                :class="{'valid': !$v.contexto.welcomeMessage.$invalid, 'invalid': $v.contexto.welcomeMessage.$invalid }" v-model="$v.contexto.welcomeMessage.$model" />
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessageEn')" for="contexto-welcomeMessageEn">welcomeMessageEn</label>
                            <input type="text" class="form-control" name="welcomeMessageEn" id="contexto-welcomeMessageEn"
                                :class="{'valid': !$v.contexto.welcomeMessageEn.$invalid, 'invalid': $v.contexto.welcomeMessageEn.$invalid }" v-model="$v.contexto.welcomeMessageEn.$model" />
                        </div>
                    </b-tab>
                    <b-tab title="Código fuente">
                        color: {{color}}
                        <b-form-input type="color" v-model="color"></b-form-input>
                        <b-card border-variant="light" title="Instalación del chatbot" :sub-title="`Configuración del contexto: << `+contexto.clave+` >>`">
                            <b-card-text>
                                Utilice el siguiente código como referencia para instalar su chatbot <b>{{ contexto.clave }}</b>
                            </b-card-text>
                        <code-highlight id="headcode-id" language="html" :key="headcodeKey">
                            {{headcode}}                        
                        </code-highlight>

                        En la sección de Body, agregue la siguiente configuración

                        <code-highlight id="bodycode-id" language="html" :key="bodycodeKey">
                            {{bodycode}}                        
                        </code-highlight>
                        </b-card>
                    </b-tab>
                </b-tabs>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contexto.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contexto-update.component.ts">
</script>
