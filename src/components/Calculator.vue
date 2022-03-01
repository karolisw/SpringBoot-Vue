<template>
  <div class="gridContainer">
    <div id="display">
      <div class="text-center">{{ currentComputedAnswer || 0 }}</div>
    </div>
    <div class="allButtons">
      <div class="numberButtons">
        <div class="numberButtons" v-for="number in numbers" :key="number">
          <div class="buttonClick" @click="numberClicked(number.value)">
            {{ number.value }}
          </div>
        </div>
      </div>
      <div class="operatorButtons">
        <div
          class="operatorButtons"
          v-for="operator in operators"
          :key="operator"
        >
          <div class="buttonClick" @click="operatorClicked(operator.value)">
            {{ operator.value }}
          </div>
        </div>
      </div>
      <div class="specialButtons">
        <div
          class="specialButtons"
          v-for="specialOperator in specialButtons"
          :key="specialOperator"
        >
          <div
            class="buttonClick"
            @click="specialButtonClicked(specialOperator.value)"
          >
            {{ specialOperator.value }}
          </div>
        </div>
      </div>
    </div>

    <div class="log">
      <div class="log" v-for="log in logList" :key="log">
        {{ log }}
      </div>
    </div>

    <footer>Karolines masterpiece 2.0</footer>
  </div>
</template>

<script>
import api from '@/services/PostService.js'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Calculator',

  data () {
    return {
      numberPresent: false,
      operatorPresent: false,
      ansPresent: false,
      recentlyCalculated: false,
      indexInCurrentFunction: 0,
      logList: [],
      entireFunction: '',
      currentFunction: '',
      tempFunction: '',
      ansSaved: '',
      numbers: [
        // also contains special buttons
        { id: 1, value: 1 },
        { id: 2, value: 2 },
        { id: 3, value: 3 },
        { id: 4, value: 4 },
        { id: 5, value: 5 },
        { id: 6, value: 6 },
        { id: 7, value: 7 },
        { id: 8, value: 8 },
        { id: 9, value: 9 },
        { id: 10, value: 0 }
      ],
      specialButtons: [
        // also contains special buttons
        { id: 11, value: 'C' },
        { id: 12, value: 'DEL' },
        { id: 13, value: 'ANS' },
        { id: 14, value: '=' }
      ],
      operators: [
        { id: 15, value: '*' },
        { id: 16, value: '/' },
        { id: 17, value: '-' },
        { id: 18, value: '+' },
        { id: 20, value: ',' }
      ]
    }
  },

  methods: {
    // eslint-disable-next-line no-unused-vars
    reset: function () {
      // --> event = c button pushed
      this.currentFunction = ''
      this.entireFunction = ''
      this.ansSaved = ''
      this.ansPresent = false
    },
    // deletes the last number
    del: function () {
      if (this.numberPresent) {
        // we remove the last number
        this.currentFunction = this.currentFunction.slice(0, -1)
      }
      if (this.currentFunction.length === 0) {
        this.numberPresent = false
      }
    },
    // this function saves what is currently inside of "current answer" for later use
    // for later use, an operator must be clicked before it
    ans: function () {
      console.log(
        'ans clicked' + 'and operator present: ' + this.operatorPresent
      )
      // if we already have an answer saved, we add it to the current function upon click
      if (this.ansPresent && this.operatorPresent) {
        this.currentFunction += this.ansSaved
        // eslint-disable-next-line brace-style
      }
      // if we did not have an answer saved, or choose to replace what we had
      else if (this.recentlyCalculated) {
        this.ansSaved = this.currentFunction
        this.ansPresent = true
        console.log('ans saved:' + this.ansSaved)
      }
    },

    calculate: async function () {
      if (this.currentFunction.length > 0) {
        this.entireFunction += this.currentFunction
        this.tempFunction = this.entireFunction
        await this.changeTempFunction(this.entireFunction)
      }
      this.logList.push(
        // eslint-disable-next-line no-eval
        this.entireFunction + ' = ' + this.tempFunction
      )
      // TODO 4. Figure out the rest of this logic...
      console.log('entire function 1:' + this.entireFunction) // works
      // eslint-disable-next-line no-eval
      // console.log('CalculatorService returns (class): ' + CalculatorService.postExpression(this.entireFunction))
      // console.log(typeof CalculatorService.postExpression(this.entireFunction))
      // this.entireFunction = CalculatorService.postExpression(this.entireFunction)
      // const result = await CalculatorService.postExpression(this.entireFunction)
      // await this.changeEntireFunction(this.entireFunction)
      console.log('entire function 2:' + this.tempFunction) // works!

      // here, current function is set to equal the answer
      this.currentFunction = this.tempFunction
      this.recentlyCalculated = true
    },
    // for use when x, %, - or + is clicked
    operatorClicked: function (thisOperator) {
      // if recently calculated or operator already added we do not want to add currentFunction to entireFunction!
      if (
        this.numberPresent &&
        !this.recentlyCalculated &&
        !this.operatorPresent) {
        this.entireFunction += this.currentFunction
        this.entireFunction += thisOperator
        console.log('entire function:' + this.entireFunction)
        this.currentFunction = ''
        this.operatorPresent = true // meaning that we cannot click another operator now
        this.numberPresent = false // meaning that the user must click a number before they can use an operator button
        // eslint-disable-next-line brace-style
      }
      // as long as there is no other operator we can add this operator
      else if (this.numberPresent && !this.operatorPresent) {
        this.entireFunction += thisOperator
        console.log('entire function:' + this.entireFunction)
        this.currentFunction = ''
        this.operatorPresent = true
        this.numberPresent = false // meaning that the user must click a number before they can use an operator button
      }
    },

    // for use when #0-9 is clicked
    numberClicked: function (thisNumber) {
      // if there was not a number present before, there is now:
      if (!this.numberPresent) {
        this.numberPresent = true
      }
      if (this.recentlyCalculated) {
        this.recentlyCalculated = false // todo check if unknown error
        this.currentFunction = ''
        this.entireFunction = ''
      }
      this.currentFunction += thisNumber.toString()
      this.operatorPresent = false // number last clicked, not operator
      console.log('currentFunction:' + this.currentFunction)
    },

    specialButtonClicked: function (thisSpecialOperator) {
      if (thisSpecialOperator === 'ANS') {
        this.ans()
      } else if (thisSpecialOperator === 'DEL') {
        this.del()
      } else if (thisSpecialOperator === 'C') {
        this.reset()
      } else if (thisSpecialOperator === '=') {
        this.calculate()
      } else {
        console.log(
          'Error when trying to call specialButtonClicked() with arg: ' +
          thisSpecialOperator.value
        )
      }
    },
    changeEntireFunction: async function (expression) {
      await api.create(expression)
        .then(function (response) {
          const result = response.data.answer
          this.entireFunction = String(result)
        }.bind(this))
    },

    changeTempFunction: async function (expression) {
      await api.create(expression)
        .then(function (response) {
          const result = response.data.answer
          this.tempFunction = String(result)
        }.bind(this))
    }
  },
  computed: {
    currentComputedAnswer () {
      return this.currentFunction
    }
  }
}
</script>
<style>

h1 {
  margin-top: 100px;
  text-align: center;
}
.gridContainer {
  display: grid;
  grid-template-columns: auto;
  width: 100%;
  overflow-x: auto;

}

#display {
  background-color: antiquewhite;
  justify-content: center;
  color: #000;
  width: 100%;
  height: 2em;
  max-width: 100%;
  align-items: center;
}
/**
.answerDisplay {
  background-color: rgb(255, 209, 148);
  padding: 15px;
  border-radius: 3px;
}

 */

.allButtons {
  width: min(450px, 70%);
  border: 1px solid rgb(255, 198, 124);
  border-radius: 4px;
  align-items: center;
  background-color: antiquewhite;
  justify-content: center;
  margin: 10px auto;
  gap: 5px;
}

.numberButtons {
  display: flex;
  background-color: rgb(255, 209, 148);
  padding: 6px;
  border-radius: 3px;
  margin: 4px;
  font-size: 1.5em;
  font-weight: bold;
  color: #000;
  text-align: center;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

.numberButtons:hover {
  background-color: rgb(238, 167, 75);
  color: #000;
}
.operatorButtons {
  display: flex;
  background-color: rgb(238, 167, 75);
  padding: 6px;
  border-radius: 3px;
  margin: 4px;
  font-size: 1.5em;
  font-weight: bold;
  color: #000;
  text-align: center;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

.operatorButtons:hover {
  background-color: rgb(238, 167, 75);
  color: #000;
}
.specialButtons {
  display: flex;
  background-color: rgb(238, 167, 75);
  color: #000;
  padding: 6px;
  border-radius: 3px;
  margin: 4px;
  font-size: 1.5em;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}
.specialButtons:hover {
  background-color: rgb(238, 167, 75);
}
footer {
  text-align: center;
  width: min(300px, 90%);
  border-radius: 3px;
  margin: 100px auto;
  background-color: #0000002a;
  padding: 10px;
  font-weight: bold;
  color: #444;
  text-transform: capitalize;
}

</style>
