import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'TEL'
})
export class TelPipe implements PipeTransform {

  transform(value: string, ...args: any[]): any {
    if (value.length === 13) {
      return value.replace(/(\d{2})(\d{2})(\d{5})(\d{4})/g, '+\$1 \$2 \$3\-\$4');
    }
    return 'error';
  }

}
