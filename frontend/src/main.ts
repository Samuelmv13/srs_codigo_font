import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { bootstrapSecurity } from '@nuvem/angular-base';

if (environment.production) {
    enableProdMode();
}

<<<<<<< HEAD

//bootstrapSecurity(environment.auth, () => {
    platformBrowserDynamic()
        .bootstrapModule(AppModule)
        .catch(err => console.log(err));
//});

=======
bootstrapSecurity(environment.auth, () => {
    platformBrowserDynamic()
        .bootstrapModule(AppModule)
        .catch(err => console.log(err));
});
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
