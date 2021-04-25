// These constants are injected via webpack environment variables.
// You can add more variables in webpack.common.js or in profile specific webpack.<dev|prod>.js files.
// If you change the values in the webpack config files, you need to re run webpack to update the application

export const STEPS = [
  {
    target: '#password',
    content: `En esta sección podrás actualizar tu <strong>Contraseña</strong>!`
  },
  {
    target: '#flujos',
    content: `Aquí puedes registrar y consultar los flujos que describen pasos en el <strong>Chatbot</strong>!`
  },
  {
    target: '#contextos',
    content: `En esta sección puedes administrar todos los contextos de tus <strong>Chatbots</strong> que has registrado hasta el momento, permitiendote incoporar nuevos bots en nuevos sitios`
  },
  {
    target: '#intenciones',
    content: `En la bitácora de <strong>Intenciones no atendidas</strong> podrás conocer cuales han sido las palabras que tu bot aún no entiende y que pueden servir para registrar nuevos flujos de atención, permitiendo que tu bot sea más inteligente`
  },
  {
    target: '#reportes',
    content: `Consulta algunos <strong>Reportes</strong> importantes que pueden ayudarte a mejorar el servicio de atención a través de chatbos`
  }
];

export const CONTEXTO_STEPS = [
  {
    target: '#hhhhhh',
    content: `Aqui es para los datos genreales`
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
