export const environment = {
    production: true,
<<<<<<< HEAD
    apiUrl: 'http://157.88.55.22:8080',
=======
    apiUrl: '/api',
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    auth: {
        baseUrl: '',
        authUrl: '/login/cas',
        loginUrl: '/login/cas',
        logoutUrl: '/cas/logout',
        detailsUrl: '/api/user/details',
        tokenValidationUrl: '/api/token/validate',
        storage: localStorage,
        tokenStorageIndex: 'token',
        userStorageIndex: 'user',
        loginSuccessRoute: ''
    }
};
