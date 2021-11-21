
export class Page<T>{
    offset: number = 0;
    limit: number=50;
    totalRecords: number = 0;
    pageSize: number=0;
    totalPages: number=0;
    currentList: T[] = [];
}