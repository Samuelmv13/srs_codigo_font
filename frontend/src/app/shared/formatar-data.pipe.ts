import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatarData'
})
export class FormatarDataPipe implements PipeTransform {

  transform(value: string, ...args: string[]): unknown {
    if (value) {
      value = value.replace(/\-/g, '/');
    };
    return value.split('/').reverse().join('/');
  }

}
