<template>
  <div class="center">
    <div class="property-card">
        <div class="property-image">
          <img :src="image" />
        </div>
        <div class="property-description">
          <slot></slot>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Card',
  props: {
    image: String
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.center
{
  height:60vh;
  width:100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.property-card
{
  height:18em;
  width:14em;
  display:flex;
  flex-direction:column;
  position:relative;
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  border-radius:16px;
  overflow:hidden;
  box-shadow:  15px 15px 27px #e1e1e3, -15px -15px 27px #ffffff;
}

.property-image
{
  height:9em;
  width:14em;
  position:Absolute;
  top:0px;
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.property-image img {
  max-height: 100%;
  max-width: 100%;
}

.property-description
{
  background-color: #d6d6d6;
  height:9em;
  width:14em;
  position:absolute;
  bottom:0em;
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  padding: 0.5em 1em;
  text-align:center;
}
</style>