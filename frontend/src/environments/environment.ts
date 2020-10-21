// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

export const environment = {
    production: false,
<<<<<<< HEAD
    apiUrl: 'http://localhost:8080/api',
=======
    apiUrl: '/api',
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    auth: {
        baseUrl: '',
        loginUrl: '/api/sso/login',
        logoutUrl: '/api/logout',
        detailsUrl: '/api/user/details',
        tokenValidationUrl: '/api/token/validate',
        storage: localStorage,
        userStorageIndex: 'user',
        loginSuccessRoute: '/#/login-success'
    }
};
