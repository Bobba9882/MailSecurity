import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'orderBy'
})
export class OrderByPipe implements PipeTransform {

  transform(array: any[], property: string, descending: boolean = false): any[] {
    if (!array || array.length === 0) {
      return [];
    }

    return array.sort((a, b) => {
      const valueA = a[property];
      const valueB = b[property];

      if (valueA < valueB) {
        return descending ? 1 : -1;
      } else if (valueA > valueB) {
        return descending ? -1 : 1;
      } else {
        return 0;
      }
    });
  }

}
