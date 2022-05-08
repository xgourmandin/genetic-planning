const data = [
    {shift: 1,
    nurses: ["N01", "N02", "N03", "N04"]
    },
    {shift: 2,
    nurses: ["N05", "N06", "N07", "N08"]
    },
    {shift: 3,
    nurses: ["N01", "N03", "N11", "N12"]
    },
    {shift: 4,
    nurses: ["N05", "N06", "N13", "N04"]
    }
]

const nurses = new Set(d3.map(data, d => d.nurses))
const shifts = d3.map(data, d => d.shift)

const svg = d3.select("#planning").append('svg')
    .attr('width', shifts.length*125)
    .attr('height', 30 * nurses.size)
    .attr("style", "max-width: 100%; height: auto; height: intrinsic;")
    .attr("font-family", "sans-serif")
    .attr("font-size", 10);

xAxsis = d3.axisTop(shifts, {
    tickSize: -30,
    tickPadding: 5,
    tickRotation: 0,
    tickValues: shifts.values()
})

svg.append("g")
    .attr("transform", "translate(0,30)")
    .call(xAxsis);


