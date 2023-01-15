import {Topic} from './topic';

export interface Category {
  id: number;
  title: string;
  description?: string;
  parentId?: number;
  children: number[];
  recentlyUpdatedTopic?: Topic;
}
