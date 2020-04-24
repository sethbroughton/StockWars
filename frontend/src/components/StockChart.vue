<template>
  <div class="small">
    <line-chart :chart-data="datacollection"></line-chart>
    <button @click="fillData()">Update</button>
  </div>
</template>

<script>
  import LineChart from '../LineChart'

  export default {
    name: 'stock-chart',
    components: {
      LineChart
    },
    data () {
      return {
        dataset: null,
        datacollection: null
      }
    },
    mounted () {
      this.fillData()
    },
    methods: {
      fillData () {
        fetch(`https://cloud.iexapis.com/v1/stock/AAPL/chart/1m?token=sk_3fbcd0adaf6a487c88f78c0027a39813`)
        .then((response) => {
          return response.json();
        })
        .then((dataset) => {
          this.dataset = dataset;
          console.log(this.dataset[0].close)
        })
        let labelsArray = [];
        this.dataset.forEach(element => {
          labelsArray.push(element.date)
        })

        this.datacollection = {
          lables: labelsArray,
          datasets: [
            {
              label: 'Data One',
              backgroundColor: '#f87979',
              data: [5, 10, 7, 5, 1, 2]
            }
          ]

             }
      },
    }
  }
</script>

<style>
  .small {
    max-width: 600px;
    margin:  150px auto;
  }
</style>