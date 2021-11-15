<template>
  <v-container fluid>
    <v-radio-group
        v-model="radioGroup"
    >
        <v-radio
          label="Ledger data recovery for failed bookie"
          value="radio-1"
        />
        <v-radio
          label="List under replicated ledgers."
          value="radio-2"
        />
        <v-radio
            label="Auditor bookie id"
            value="radio-3"
        />
        <v-radio
            label="Force trigger audit by resting the lostBookieRecoveryDelay"
            value="radio-4"
        />
        <v-radio
            label="Update lostBookieRecoveryDelay value in seconds"
            value="radio-5"
        />
        <v-radio
            label="Decommission Bookie"
            value="radio-6"
        />
        <v-radio
            label="Garbage collector details"
            value="radio-7"
        />
        <v-radio
            label="Trigger garbage collector"
            value="radio-8"
        />
    </v-radio-group>
  </v-container>
</template>

<script>
export default {
    data() {
        return {
            radioGroup: 1,
        }
    },
}

</script>
