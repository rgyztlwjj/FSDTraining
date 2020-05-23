import { Component, OnInit } from '@angular/core';
import { UploadFile } from 'ng-zorro-antd/upload';

function getBase64(file: File): Promise<string | ArrayBuffer | null> {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}
@Component({
  selector: 'app-upload-picture',
  templateUrl: './upload-picture.component.html',
  styleUrls: ['./upload-picture.component.css']
})
export class UploadPictureComponent {

  fileList = [
    {
      uid: '-1',
      name: 'image.png',
      status: 'done',
      url: '../../../assets/pictures/samsung1.jpg'
    },
    {
      uid: '-2',
      name: 'image.png',
      status: 'done',
      url: '../../../assets/pictures/samsung2.jpg'
    },

  ];
  previewImage: string | undefined = '';
  previewVisible = false;

  handlePreview = async (file: UploadFile) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj!);
    }
    this.previewImage = file.url || file.preview;
    this.previewVisible = true;
  };
}
