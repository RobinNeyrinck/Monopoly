"use strict";

const _config = {
    groupnumber: '02',
    timeDelay: 1500,
    gamePrefix: 'group02',
    errorHandlerSelector: '.errormessages p',
    getAPIUrl: function() { return `https://project-i.ti.howest.be/monopoly-${this.groupnumber}/api`;}
};
