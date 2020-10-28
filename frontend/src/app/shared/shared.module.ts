import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { FormatarDataPipe } from './formatar-data.pipe';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        FormatarDataPipe,
    ],
    declarations: [FormatarDataPipe]
})
export class SharedModule { }
