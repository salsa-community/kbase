// These constants are injected via webpack environment variables.
// You can add more variables in webpack.common.js or in profile specific webpack.<dev|prod>.js files.
// If you change the values in the webpack config files, you need to re run webpack to update the application

export const STEPS = [
  {
    target: '#password',
    content: `En esta sección podrás actualizar tu <strong>Contraseña</strong>!`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('password-router-id').click();
        resolve('next');
      })
  },
  {
    target: '#flujos',
    content: `Aquí puedes registrar y consultar los flujos que describen pasos en el <strong>Chatbot</strong>!`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('flujos-router-id').click();
        resolve('next');
      })
  },
  {
    target: '#buscador-id',
    content: `Puedes buscar un flujo a partir del <strong>código</strong> con el que lo registraste`,
    params: {
      enableScrolling: true
    }
  },
  {
    target: '#create-flujo-id',
    content: `Puedes crear un nuevo <strong>flujo</strong> con sus respectivos pasos`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('flujos-router-id').click();
        resolve('next');
      })
  },
  {
    target: '#contextos',
    content: `En esta sección puedes administrar todos los contextos de tus <strong>Chatbots</strong> que has registrado hasta el momento, permitiendote incoporar nuevos bots en nuevos sitios`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('contextos-router-id').click();
        resolve('next');
      })
  },
  {
    target: '#create-contexto-id',
    content: `Puedes crear un nuevo <strong>contexto</strong> para un bot que puede ser utilizado en un nuevo sitio web`,
    params: {
      enableScrolling: true
    }
  },
  {
    target: '#intenciones',
    content: `En la bitácora de <strong>Intenciones no atendidas</strong> podrás conocer cuales han sido las palabras que tu bot aún no entiende y que pueden servir para registrar nuevos flujos de atención, permitiendo que tu bot sea más inteligente`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('intenciones-router-id').click();
        resolve('next');
      })
  },
  {
    target: '#reportes',
    content: `Consulta algunos <strong>Reportes</strong> importantes que pueden ayudarte a mejorar el servicio de atención a través de chatbots`,
    params: {
      enableScrolling: true
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('reportes-router-id').click();
        resolve('next');
      })
  }
];

export const CONTEXTO_STEPS = [
  {
    target: '#datos-generales-id',
    content: `En esta sección se registran los principales datos del contexto, los cuales podrás utilizar más tarde para registrar tu bot en un sitio web`,
    params: {
      enableScrolling: false
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('datos-generales-id').click();
        resolve('next');
      })
  },
  {
    target: '#contexto-clave',
    content: `Este valor, es el que se utiliza para registrar un bot en un sitio web`,
    params: {
      enableScrolling: false
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('datos-generales-id').click();
        resolve('next');
      })
  },
  {
    target: '#datos-organizacion-id',
    content: `En esta sección se registran los datos de tu organización o negocio. Los datos que registres aquí se utilizarán en los diálogos de bienvenida de tu bot`,
    params: {
      enableScrolling: false
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('datos-organizacion-id').click();
        resolve('next');
      })
  },
  {
    target: '#datos-chatbot-id',
    content: `En esta sección, se registran los mensajes que tu mostrará tu bot cuando solicite las credenciales para autenticación y el mensaje de bienvenidad una vez que la autenticación es exitosa.`,
    params: {
      enableScrolling: false
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('datos-chatbot-id').click();
        resolve('next');
      })
  },
  {
    target: '#datos-codigo-id',
    content: `En esta sección podrás consultar un ejemplo de código fuente que te será útil para configurar tu bot en un sitio web`,
    params: {
      enableScrolling: false
    },
    before: type =>
      new Promise((resolve, reject) => {
        document.getElementById('datos-codigo-id').click();
        resolve('next');
      })
  }
];

export const OPTIONS = {
  highlight: true,
  labels: {
    buttonSkip: 'Quitar tutorial',
    buttonPrevious: 'Anterior',
    buttonNext: 'Siguiente',
    buttonStop: 'Terminar'
  }
};
