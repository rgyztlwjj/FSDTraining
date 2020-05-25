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
      url: '../../../assets/img/iPhone11.jpg'
    },
    {
      uid: '-2',
      name: 'image.png',
      status: 'done',
      url: '../../../assets/img/samsung2.jpg'
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

  upLoadChange(event) {
    let file = event ? event.file : null;
    let datas = file && file.id ? file : file.response && file.response.rlt == 0 && file.response.datas;
    if (datas) {
        if (event.type == 'success') {
            datas['uid'] = datas.id;
            this.fileList.push(datas);
            // console.log('after upload', this.station.resourceimageList);
        } else if (event.type == 'removed') {
            this.deleteImageById(datas.id, this.fileList);
            // console.log('after removed', this.station.resourceimageList);
          }
      }
  }

  private getInfo(id) {
    // this.service.getInfo(id).then(res => {
    //     // console.log('编辑页详细数据', res)
    //     if (res) {
    //         this.fileList = res;
    //         if (res.resourceimageList && res.resourceimageList.length > 0) {
    //             res.resourceimageList.forEach(element => {
    //                 element['uid'] = element.id; // 插件需要通过uid 来删除其中的数据
    //                 element['url'] = '/harbor_upload/' + element.saveName;
    //               })
    //           }
    //       } else {
    //           console.log('接口调用失败')
    //       }
    //   })
  }


  private deleteImageById(id, arr) {
    let index = arr.findIndex(ele => ele.id == id)
    if (arr.length > 0 && index != -1) {
        arr.splice(index, 1);
      }
  }
}
