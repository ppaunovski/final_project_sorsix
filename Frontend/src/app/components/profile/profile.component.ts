import {AfterViewInit, Component, ElementRef, OnInit, ViewChild,} from '@angular/core';
import {UserAccountService} from '../../service/user-account.service';
import {UserAccount} from '../../model/UserAccount';
import {BaseChartDirective} from 'ng2-charts';
import {ProfitsPerProperty} from '../../model/ProfitsPerProperty';
import * as echarts from 'echarts';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit, AfterViewInit {
  @ViewChild('canvas')
  canvas!: ElementRef<any>;

  user: UserAccount | undefined;
  profits: ProfitsPerProperty[] = [];
  data: any[] = [];

  constructor(private userService: UserAccountService) {
  }

  ngAfterViewInit(): void {
    var myChart = echarts.init(this.canvas.nativeElement);
    var option: echarts.EChartsOption;

    this.userService.getProfitsPerProperty().subscribe({
      next: (profits) => {
        console.log(profits);

        this.profits = profits;
        this.profits.forEach((profit) => {
          this.data.push({
            value: profit.totalProfit / 86400 / 1000000000,
            name: profit.propertyName,
          });
        });

        option = {
          title: {
            text: 'Profits per Property',
            subtext: '',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: '50%',
              data: this.data,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                },
              },
            },
          ],
        };

        option && myChart.setOption(option);
      },
    });
  }

  ngOnInit(): void {
    this.userService.getUserInfo().subscribe((user) => {
      this.user = user;
    });
  }
}
